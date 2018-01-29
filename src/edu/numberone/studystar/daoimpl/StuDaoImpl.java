package edu.numberone.studystar.daoimpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import edu.numberone.studystar.utils.DBUtils;

public class StuDaoImpl {

	public String AchieveName(String status,String id,String password) {
		String table=null;
		String STM_Name = null;
		String STM_ID=null;
		String STM_Pass=null;
		switch (status) {
		case "student":
			table = "student";
			STM_Name = "S_Name";
			STM_ID = "S_ID";
			STM_Pass = "S_Pass";
			break;
		case "teacher":
			table = "teacher";
			STM_Name = "T_Name";
			STM_ID = "T_ID";
			STM_Pass = "T_Pass";
			break;
		case "admin":
			table = "manager";
			STM_Name = "M_Name";
			STM_ID = "M_ID";
			STM_Pass = "M_Pass";
			break;
		}
		
		Connection conn = DBUtils.getInstance().openConnection();
		String sql ="";
		sql += "select "+STM_Name;
		sql	+= " from "+table;
		sql	+= " where "+STM_ID+"=?";
		sql += " and "+STM_Pass+"=?";

		try {
			// 创建执行对象
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.setString(1, id);
			pstat.setString(2, password);
			// pstat.executeQuery()会返回一个结果集
			ResultSet rs =pstat.executeQuery();
			// 调用rs重的next()方法，如果结果集有数据则为真，那么则表示有数据
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭数据库连接
			//util.disConnection(conn);
		}
		// 返回用户是否在表中存在的布尔值
		return null;
	}

}
