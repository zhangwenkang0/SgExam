package edu.numberone.studystar.jdbc;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface JdbcQuery
{
    /**
     * 
     * @author 
     * 封装jdbc操作数据库的接口定义
     * 
     */
    public abstract void setParameter(String parameterName, Object value);

    public abstract void setParameter(int parameterIndex, Object value);
    
    public abstract void setParameters(Map<String, Object> params);

    public abstract void setParameter(String parameterName, Object value,
            boolean isDbFunction);

    public abstract void setOutParameter(int parameterIndex, int sqlType);

    public abstract void setOutParameter(String parameterName, int sqlType);

    public abstract int getInt(String parameterName) throws SQLException;

    public abstract int getInt(int parameterIndex) throws SQLException;

    public abstract Object getObject(String parameterName) throws SQLException;

    public abstract Object getObject(int parameterIndex) throws SQLException;

    public abstract String getString(String parameterName) throws SQLException;

    public abstract String getString(int parameterIndex) throws SQLException;

    public abstract boolean getBoolean(String parameterName) throws SQLException;

    public abstract boolean getBoolean(int parameterIndex) throws SQLException;

    public abstract ResultSet getResultSet(String parameterName) throws SQLException;

    public abstract ResultSet getResultSet(int parameterIndex) throws SQLException;

    public abstract List<?> getResultList(String parameterName);

    public abstract List<?> getResultList(int parameterIndex);

    public abstract <T> List<T> getResultList(String parameterName,
            Class<T> classType);

    public <T> List<T> getResultList(int parameterIndex, Class<T> classType);

    public abstract CallableStatement excuteProcedure();

    public abstract int excuteUpdate();

    public abstract <T> T getBean();

    public abstract Map<String, Object> getHashMap();

    public abstract Object getSingleResult();
    
    public abstract int getCount();

    public abstract List<?> getResultList();
    
    public abstract List<?> getStringList();

    public abstract List<?> getResultListArray();

    public abstract void closeCallableStatement();

    public abstract void closeConnection();
}
