package edu.numberone.studystar.jdbc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class AbstractJdbcQueryImpl implements JdbcQuery {
	/**
	 * 
	 * @author 王威 实现接口JdbcQuery的抽象类 此类如同模板一样，只包含处理逻辑， 具体动作的交给子类实现
	 * 
	 */
	protected String scolon = ":";
	protected String character = "'";
	protected String strSql = null;
	protected Connection conn = null;
	protected ResultSet rs = null;
	protected PreparedStatement ps = null;
	protected CallableStatement proc = null;
	protected Class<?> classType = null;
	
	protected Map<String, Integer> paramsToIndex = null;
	protected Map<String, Object> sparams = new HashMap<String, Object>();
	protected Map<Integer, Object> iparams = new HashMap<Integer, Object>();
	protected Map<String, Integer> soutParams = new HashMap<String, Integer>();
	protected Map<Integer, Integer> ioutParams = new HashMap<Integer, Integer>();
	protected Map<String, Boolean> dbFunction = new HashMap<String, Boolean>();

	protected abstract void clearMap();

	protected abstract int indexOfParameterName(String parameterName);

	protected abstract void setParameters(String parameterName, Object value);

	protected abstract void setParameters(int parameterIndex, Object value);

	protected abstract void setDbFunctionParameter(String parameterName,
			boolean isDbFunction);

	protected abstract void setOutParameters(String parameterName, int sqlType);

	protected abstract void setOutParameters(int parameterIndex, int sqlType);

	protected abstract PreparedStatement getPreparedStatement();

	protected abstract CallableStatement getCallableStatement();

	public AbstractJdbcQueryImpl(Connection conn, String strSql) {
		clearMap();
		this.conn = conn;
		this.strSql = strSql;
		validateConnction();
	}
	
	public AbstractJdbcQueryImpl(Connection conn, String strSql, Map<String,Object> params) {
		clearMap();
		this.conn = conn;
		this.strSql = strSql;
		this.setParameters(params);
		validateConnction();
	}

	public AbstractJdbcQueryImpl(Connection conn, String strSql,
			Class<?> classType) {
		clearMap();
		this.conn = conn;
		this.strSql = strSql;
		this.classType = classType;
		validateConnction();
	}
	
	public AbstractJdbcQueryImpl(Connection conn, String strSql,
			Class<?> classType, Map<String,Object> params) {
		clearMap();
		this.conn = conn;
		this.strSql = strSql;
		this.classType = classType;
		this.setParameters(params);
		validateConnction();
	}

	public void setParameters(Map<String, Object> params) {
		for(String key : params.keySet()) {
			if(strSql.indexOf(scolon+key) == -1) 
				continue;
			
			Object value = params.get(key);
			setParameter(key.toString(), value);
		}
	}

	public void setParameter(String parameterName, Object value) {
		setParameters(parameterName, value);
	}

	public void setParameter(int parameterIndex, Object value) {
		setParameters(parameterIndex, value);
	}

	public void setParameter(String parameterName, Object value,
			boolean isDbFunction) {
		setParameters(parameterName, value);
		setDbFunctionParameter(parameterName, isDbFunction);
	}

	public void setOutParameter(int parameterIndex, int sqlType) {
		setOutParameters(parameterIndex, sqlType);
	}

	public void setOutParameter(String parameterName, int sqlType) {
		setOutParameters(parameterName, sqlType);
	}

	// =============调用存储过程后通过以上方法取出输出参数返回的值Begin===========================
	public Object getObject(String parameterName) throws SQLException {
		return this.proc.getObject(indexOfParameterName(parameterName));
	}

	public Object getObject(int parameterIndex) throws SQLException {
		return this.proc.getObject(parameterIndex);
	}

	public String getString(String parameterName) throws SQLException {
		return this.proc.getString(indexOfParameterName(parameterName));
	}

	public String getString(int parameterIndex) throws SQLException {
		return this.proc.getString(parameterIndex);
	}

	public int getInt(String parameterName) throws SQLException {
		return this.proc.getInt(indexOfParameterName(parameterName));
	}

	public int getInt(int parameterIndex) throws SQLException {
		return this.proc.getInt(parameterIndex);
	}

	public boolean getBoolean(String parameterName) throws SQLException {
		return this.proc.getBoolean(indexOfParameterName(parameterName));
	}

	public boolean getBoolean(int parameterIndex) throws SQLException {
		return this.proc.getBoolean(parameterIndex);
	}

	public ResultSet getResultSet(String parameterName) throws SQLException {
		return (ResultSet) this.proc
				.getObject(indexOfParameterName(parameterName));
	}

	public ResultSet getResultSet(int parameterIndex) throws SQLException {
		return (ResultSet) this.proc.getObject(parameterIndex);
	}

	public List<?> getResultList(String parameterName) {
		List<?> dataList = null;
		try {
			rs = (ResultSet) this.proc
					.getObject(indexOfParameterName(parameterName));
			if (classType == null) {
				dataList = ResultSetUtils.toList(rs);
			} else {
				dataList = ResultSetUtils.toList(rs, classType);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			this.closeResultSet();
		}
		return dataList;
	}

	public List<?> getResultList(int parameterIndex) {
		List<?> dataList = null;
		try {
			rs = (ResultSet) this.proc.getObject(parameterIndex);
			if (classType == null) {
				dataList = ResultSetUtils.toList(rs);
			} else {
				dataList = ResultSetUtils.toList(rs, classType);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			this.closeResultSet();
		}
		return dataList;
	}

	public <T> List<T> getResultList(String parameterName, Class<T> classType) {
		List<T> dataList = null;
		try {
			rs = (ResultSet) this.proc
					.getObject(indexOfParameterName(parameterName));
			dataList = ResultSetUtils.toList(rs, classType);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			this.closeResultSet();
		}
		return dataList;
	}

	public <T> List<T> getResultList(int parameterIndex, Class<T> classType) {
		List<T> dataList = null;
		try {
			rs = (ResultSet) this.proc.getObject(parameterIndex);
			dataList = ResultSetUtils.toList(rs, classType);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			this.closeResultSet();
		}
		return dataList;
	}

	// =============调用存储过程后通过以上方法取出输出参数返回的值End=============================

	// 执行存储过程返回CallableStatement
	public CallableStatement excuteProcedure() {
		try {
			this.proc = getCallableStatement();
			this.proc.execute();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return this.proc;
	}

	public int excuteUpdate() {
		int result = 0;
		try {
			ps = this.getPreparedStatement();
			result = ps.executeUpdate();
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			this.closePreparedStatement();
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public <T> T getBean() {
		T entity = null;
		try {
			if (classType == null) {
				throw new RuntimeException("并未传入所需的实体类型！");
			}
			ps = this.getPreparedStatement();
			rs = ps.executeQuery();
			entity = (T) ResultSetUtils.toBean(rs, classType);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			this.closeResultSet();
			this.closePreparedStatement();
		}

		return entity;
	}

	public Map<String, Object> getHashMap() {
		Map<String, Object> map = null;
		try {
			ps = this.getPreparedStatement();
			rs = ps.executeQuery();
			map = ResultSetUtils.toHashMap(rs);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			this.closeResultSet();
			this.closePreparedStatement();
		}

		return map;
	}

	public Object getSingleResult() {
		Object result = null;
		try {
			ps = this.getPreparedStatement();
			rs = ps.executeQuery();
			result = ResultSetUtils.toSingleResult(rs);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			this.closeResultSet();
			this.closePreparedStatement();
		}

		return result;
	}
	
	public int getCount() {
		Object value = this.getSingleResult();
    	if(value == null) value = 0;
    	
    	int count = Integer.parseInt(value.toString());
    	return count;
	}

	public List<?> getResultList() {
		List<?> entityList = null;
		try {
			ps = this.getPreparedStatement();
			rs = ps.executeQuery();
			if (classType == null) {
				entityList = ResultSetUtils.toList(rs);
			} else {
				entityList = ResultSetUtils.toList(rs, classType);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResultSet();
			this.closePreparedStatement();
		}

		return entityList;
	}
	
	public List<String> getStringList() {
		List<String> entityList = null;
		try {
			ps = this.getPreparedStatement();
			rs = ps.executeQuery();
			if (classType == null) {
				entityList = ResultSetUtils.toStringList(rs);
			} 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.closeResultSet();
			this.closePreparedStatement();
		}

		return entityList;
	}

	@SuppressWarnings("unchecked")
	public List getResultListArray() {
		List arrayList = null;
		try {
			ps = this.getPreparedStatement();
			rs = ps.executeQuery();
			arrayList = ResultSetUtils.toArrayList(rs);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		} finally {
			this.closeResultSet();
			this.closePreparedStatement();
		}

		return arrayList;
	}

	private void validateConnction() {
		try {
			if (this.conn == null) {
				throw new RuntimeException("数据库连接对象为空！");
			}
			if (this.conn.isClosed()) {
				throw new RuntimeException("数据库连接已关闭！");
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private void closeResultSet() {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			rs = null;
		}
	}

	public void closeCallableStatement() {
		if (proc != null) {
			try {
				proc.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			proc = null;
		}
	}

	private void closePreparedStatement() {
		if (ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			ps = null;
		}
	}

	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			conn = null;
		}
	}
}
