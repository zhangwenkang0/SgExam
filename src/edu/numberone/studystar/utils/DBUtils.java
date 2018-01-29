package edu.numberone.studystar.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

/**
 * 单例模式创建数据库连接类，用于连接数据库
 * 
 */

public class DBUtils {

	// 采用饿汉模式创建单列模式
	private static DBUtils dbUtils = new DBUtils();

	private DBUtils() {

	}

	public static DBUtils getInstance() {
		return dbUtils;
	}

	/**
	 * 获得Connection
	 * 
	 * @param
	 * @return 返回获得的Connection对象
	 * 
	 */
	public Connection openConnection() {

		String driver = null;
		String url = null;
		String username = null;
		String password = null;

		Properties pro = new Properties();
		InputStream input = this.getClass().getClassLoader().getResourceAsStream("config/jdbc.properties");

		try {
			pro.load(input);
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			username = pro.getProperty("user");
			password = pro.getProperty("password");

			try {
				Class.forName(driver);
				try {
					return DriverManager.getConnection(url, username, password);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 关闭Connection
	 * 
	 * @param conn
	 *            需要关闭的Conection对象
	 * @return
	 * 
	 */
	public void disConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * 关闭statement
	 * 
	 * @param statement
	 *            需要关闭的statement对象
	 * @return
	 * 
	 */
	public void disSatement(Statement statement) {

		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	
	/**
	 * 关闭resultSet
	 * 
	 * @param resultSet
	 *            需要关闭的resultSet对象
	 * @return
	 * 
	 */
	public void disResultSet(ResultSet resultSet) {

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
