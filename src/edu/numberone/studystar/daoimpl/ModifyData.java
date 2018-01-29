package edu.numberone.studystar.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



import edu.numberone.studystar.utils.DBUtils;

public class ModifyData {
	private Connection conn;
	private PreparedStatement pst;
	private ResultSet rs;
	public String pass;
	//修改密码的步骤：
	//1.通过已经登录的用户名查询出其原密码，
	//2.获取旧密码框，新密码框和确认密码框中输入的值，
	//3.判断原密码与旧密码框值是否相等，如果相等，则接着判断新密码框和确认密码框中输入的值是否一致，如果一致，就把新密码替换掉原密码
	
	//String sql1="select S_Pass from Student where S_Name=?";//S_Name或S_ID;
	//sql2="update Student set S_Pass=? where S_Name=?";
	public boolean ModifyPass(String sql1,String sql2,String username,String oldpass,String newpass,String repass){
		 
		 
		 try {
			 //根据登录用户查询其密码
			//sql1="select S_Pass from Student where S_Name=?";//S_Name或S_ID
			conn=DBUtils.getInstance().openConnection();
			pst=conn.prepareStatement(sql1);
			pst.setString(1,username);
			rs=pst.executeQuery();
			while(rs.next()){
				//String pass=rs.getString(1);   //从结果集中得到保存在数据库里的用户密码
				pass=rs.getString(1);
			
			if(pass.equals(oldpass)&&newpass.equals(repass)){
				//sql2="update Student set S_Pass=? where S_Name=?";
				pst=conn.prepareStatement(sql2);
				pst.setString(1, newpass);
				pst.setString(2, username);
				pst.executeUpdate();
			}else{
				System.out.print("密码修改失败！  ");
				if(!pass.equals(oldpass)){
					System.out.println("您所填写的旧密码错误！");
				}
				return false;
				//System.out.println("密码修改失败！");
				
			}
			}
			
			rs.close();
			pst.close();
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}
}
