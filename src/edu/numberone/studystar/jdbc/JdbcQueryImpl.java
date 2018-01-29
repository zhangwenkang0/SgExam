package edu.numberone.studystar.jdbc;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.text.MessageFormat;

@SuppressWarnings("unchecked")
public class JdbcQueryImpl extends AbstractJdbcQueryImpl {
	
	private static String paramsFormat = "[{0}:{1}]";
	
	/**
	 * 
	 * @author 实现父类的抽象方法 若要修改具体实现， 只需修改此类或新增一个类， 继承抽象父类AbstractJdbcQueryImpl
	 * 
	 */
	public JdbcQueryImpl(Connection conn, String strSql) {
		super(conn, strSql);
	}
	
	public JdbcQueryImpl(Connection conn, String strSql, Map<String,Object> params) {
		super(conn, strSql, params);
	}

	public JdbcQueryImpl(Connection conn, String strSql, Class<?> classType) {
		super(conn, strSql, classType);
	}
	
	public JdbcQueryImpl(Connection conn, String strSql, Class<?> classType, Map<String,Object> params) {
		super(conn, strSql, classType, params);
	}

	protected void clearMap() {
		if (sparams != null && !sparams.isEmpty())
			sparams.clear();
		if (iparams != null && !iparams.isEmpty())
			iparams.clear();
		if (soutParams != null && !soutParams.isEmpty())
			soutParams.clear();
		if (ioutParams != null && !ioutParams.isEmpty())
			ioutParams.clear();
		if (dbFunction != null && !dbFunction.isEmpty())
			dbFunction.clear();
		if (paramsToIndex != null && !paramsToIndex.isEmpty())
			paramsToIndex.clear();
	}

	@Override
	protected int indexOfParameterName(String parameterName) {
		return paramsToIndex.get(parameterName.trim());
	}

	@Override
	protected void setParameters(String parameterName, Object value) {
		if (sparams.containsKey(returnName(parameterName))) {
			throw new RuntimeException("已存在命名参数：" + parameterName);
		}
		sparams.put(returnName(parameterName), returnValue(value));
	}

	@Override
	protected void setParameters(int parameterIndex, Object value) {
		if (iparams.containsKey(parameterIndex)) {
			throw new RuntimeException("已存在索引号：" + parameterIndex);
		}
		iparams.put(parameterIndex, returnValue(value));
	}

	@Override
	protected void setDbFunctionParameter(String parameterName,
			boolean isDbFunction) {
		dbFunction.put(returnName(parameterName), isDbFunction);
	}

	@Override
	protected void setOutParameters(String parameterName, int sqlType) {
		if (soutParams.containsKey(returnName(parameterName))) {
			throw new RuntimeException("已存在命名参数：" + parameterName);
		}
		soutParams.put(returnName(parameterName), sqlType);
	}

	@Override
	protected void setOutParameters(int parameterIndex, int sqlType) {
		if (ioutParams.containsKey(parameterIndex)) {
			throw new RuntimeException("已存在索引号：" + parameterIndex);
		}
		ioutParams.put(parameterIndex, sqlType);
	}

	@Override
	protected PreparedStatement getPreparedStatement() {
		String skey, strTemp, printSql = "";
		Map<Integer, String> indexToParamName = new HashMap<Integer, String>();
		Map<Integer, Object> indexToParamValue = new HashMap<Integer, Object>();
		
		if (!sparams.isEmpty()) {
			int pos, n = 1;
			paramsToIndex = new HashMap<String, Integer>();
			Set<String> keys = sparams.keySet();
			List<Integer> list = new ArrayList<Integer>();
			
			for (String key : keys) {
				skey = scolon + key;
				if (strSql.indexOf(skey) < 0) {
					throw new RuntimeException("SQL语句中不存在" + skey + "参数名称！");
				}
				if (dbFunction.containsKey(key) && dbFunction.get(key)) {
					strSql = strSql.replace(skey,
							String.valueOf(sparams.get(key)));
					continue;
				}
				pos = strSql.indexOf(skey);
				indexToParamName.put(pos, key);
				list.add(pos);
				strTemp = strSql.replaceFirst(skey, "");
				while (strTemp.indexOf(skey) > -1) {
					pos = skey.length() * n + strTemp.indexOf(skey);
					indexToParamName.put(pos, key);
					list.add(pos);
					strTemp = strTemp.replaceFirst(skey, "");
					n++;
				}
			}
			
			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				String key = indexToParamName.get(list.get(i));
				Object value = sparams.get(key);
				paramsToIndex.put(key, i + 1);
				indexToParamValue.put(i + 1, value);
			}
			
			printSql = strSql;
			for (String key : keys) {
				skey = scolon + key;
				strSql = strSql.replace(skey, "?");
				if (dbFunction.containsKey(key)) {
					String fmtVal = MessageFormat.format(paramsFormat, key, String.valueOf(sparams.get(key)));
					printSql = printSql.replace(skey, fmtVal);
				} else {
					String fmtVal = MessageFormat.format(paramsFormat, key, quotedStr(sparams.get(key)));
					printSql = printSql.replace(skey, fmtVal);
				}
			}
		}

		if (!"".equals(printSql))
			System.out.println("SQL命令=>" + printSql);

		try {
			this.ps = conn.prepareStatement(strSql);
			setPreparedStatementParameters(iparams);
			setPreparedStatementParameters(indexToParamValue);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return this.ps;
	}

	@Override
	protected CallableStatement getCallableStatement() {
		String skey, printSql = "";
		Map<Integer, String> indexToParamName = new HashMap<Integer, String>();
		Map<Integer, Object> indexToParamValue = new HashMap<Integer, Object>();
		Map<Integer, String> indexToOutParamName = new HashMap<Integer, String>();
		Map<Integer, Integer> indexToOutParamValue = new HashMap<Integer, Integer>();
		if (!sparams.isEmpty() || !soutParams.isEmpty()) {
			int pos;
			paramsToIndex = new HashMap<String, Integer>();
			Set<String> keys = sparams.keySet();
			List<Integer> list = new ArrayList<Integer>();
			
			for (String key : keys) {
				skey = scolon + key;
				pos = strSql.indexOf(skey);
				if (pos < 0) {
					throw new RuntimeException("SQL语句中不存在" + skey + "参数名称！");
				}

				indexToParamName.put(pos, key);
				list.add(pos);
			}

			for (String key : soutParams.keySet()) {
				skey = scolon + key;
				pos = strSql.indexOf(skey);
				if (pos < 0) {
					throw new RuntimeException("SQL语句中不存在" + skey + "参数名称！");
				}

				indexToOutParamName.put(pos, key);
				list.add(pos);
			}

			Collections.sort(list);
			for (int i = 0; i < list.size(); i++) {
				String key;
				Object value;
				pos = list.get(i);
				if (indexToParamName.containsKey(pos)) {
					key = indexToParamName.get(pos);
					value = sparams.get(key);
					paramsToIndex.put(key, i + 1);
					indexToParamValue.put(i + 1, value);
				}

				if (indexToOutParamName.containsKey(pos)) {
					key = indexToOutParamName.get(pos);
					value = soutParams.get(key);
					paramsToIndex.put(key, i + 1);
					indexToOutParamValue.put(i + 1,
							Integer.parseInt(String.valueOf(value)));
				}
			}

			printSql = strSql;
			for (String key : keys) {
				skey = scolon + key;
				strSql = strSql.replace(skey, "?");
				
				String fmtVal = MessageFormat.format(paramsFormat, key, quotedStr(sparams.get(key)));
				printSql = printSql.replace(skey, fmtVal);
			}

			for (String key : soutParams.keySet()) {
				skey = scolon + key;
				strSql = strSql.replace(skey, "?");
				
				String fmtVal = MessageFormat.format(paramsFormat, key, "null");
				printSql = printSql.replace(skey, fmtVal);
			}
		}
		
		if (!"".equals(printSql))
			System.out.println("存储过程=>" + printSql);

		try {
			this.proc = conn.prepareCall(strSql);
			setCallableStatementParameters(indexToParamValue);
			setCallableStatementParameters(iparams);
			setCallableStatementOutParameters(indexToOutParamValue);
			setCallableStatementOutParameters(ioutParams);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}

		return this.proc;
	}

	private void setPreparedStatementParameters(Map<Integer, Object> params)
			throws SQLException {
		for (int i : params.keySet()) {
			this.ps.setObject(i, params.get(i));
		}
	}

	private void setCallableStatementParameters(Map<Integer, Object> params)
			throws SQLException {
		for (int i : params.keySet()) {
			this.proc.setObject(i, params.get(i));
		}
	}

	private void setCallableStatementOutParameters(Map<Integer, Integer> params)
			throws SQLException {
		for (int i : params.keySet()) {
			this.proc.registerOutParameter(i, params.get(i));
		}
	}

	private String quotedStr(Object value) {
		String str = String.valueOf(value);
		return character + str + character;
	}

	private String returnName(String name) {
		return String.valueOf(name).trim();
	}

	private Object returnValue(Object value) {
		if (value == null)
			value = "";
		return value;
	}
}
