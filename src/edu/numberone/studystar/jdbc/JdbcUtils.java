package edu.numberone.studystar.jdbc;

import java.sql.Connection;
import java.util.Map;

import edu.numberone.studystar.utils.DBUtils;

public class JdbcUtils
{
    /**
     * 
     * @author  用于对jdbc操作数据库的简单封装，使得代码编写简单
     * 
     */
	private static Connection conn=null;
	static{
	conn = DBUtils.getInstance().openConnection();
	}

    public static JdbcQuery createNativeQuery( String strSql)
    {
        return new JdbcQueryImpl(conn, strSql);
    }
    
    public static JdbcQuery createNativeQuery( String strSql, Map<String,Object> params)
    {
        return new JdbcQueryImpl(conn, strSql, params);
    }

	public static JdbcQuery createNativeQuery( String strSql,
            Class classType)
    {
        return new JdbcQueryImpl(conn, strSql, classType);
    }
	
	public static JdbcQuery createNativeQuery(String strSql,
            Class classType, Map<String,Object> params)
    {
        return new JdbcQueryImpl(conn, strSql, classType, params);
    }
}
