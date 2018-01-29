package edu.numberone.studystar.daoimpl;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import edu.numberone.studystar.entity.ClassManageBean;
import edu.numberone.studystar.entity.Student;
import edu.numberone.studystar.utils.DBUtils;

public class ScanClassManageInfoDaoImple {
	private Connection conn;
	private Statement st;
	private ResultSet rs;
	private PreparedStatement pst;
	
	public List<ClassManageBean> classQuery(){
		rs=null;
		
		 try {
			
			String sql="SELECT cls_id,cls_name,coll_name FROM classes,college WHERE classes.`COLL_ID`=college.`COLL_ID`";

			conn=DBUtils.getInstance().openConnection();
			st=conn.createStatement();
			rs=st.executeQuery(sql);
			List<ClassManageBean> list=new ArrayList<ClassManageBean>();
			while(rs.next()){
				/*System.out.print(rs.getString(1)+" ");
				System.out.print(rs.getString(2)+" ");
				System.out.println(rs.getString(3)+" ");*/
				ClassManageBean cmb=new ClassManageBean();
				cmb.setCls_id(rs.getString(1));
				cmb.setCls_name(rs.getString(2));
				cmb.setColl_name(rs.getString(3));
				
				System.out.print(cmb.getCls_id()+" ");
				System.out.print(cmb.getCls_name()+" ");
				System.out.println(cmb.getColl_name()+" ");
				list.add(cmb);
				//System.out.println(list);
				
			}
			return list;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.getInstance().disResultSet(rs);
			DBUtils.getInstance().disSatement(st);
			DBUtils.getInstance().disConnection(conn);
		}
		 return null;
		 
	}
	
	public List<Student> studentQuery(String classid){
		rs=null;
		try {
			String sql="SELECT S_ID,S_Name,S_Sex FROM Student,classes WHERE student.cls_id=classes.cls_id and classes.cls_id=?";
			conn=DBUtils.getInstance().openConnection();
			pst=conn.prepareStatement(sql);
			pst.setString(1, classid);
			rs=pst.executeQuery();
			List<Student> list=new ArrayList<Student>();
			while(rs.next()){
				Student std=new Student();
				std.setS_id(rs.getString(1));
				std.setS_name(rs.getString(2));
				std.setS_sex(rs.getString(3));
				
				System.out.print(std.getS_id()+" ");
				System.out.print(std.getS_name()+" ");
				System.out.println(std.getS_sex()+" ");
				list.add(std);
			}
			return list;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtils.getInstance().disResultSet(rs);
			DBUtils.getInstance().disSatement(pst);
			DBUtils.getInstance().disConnection(conn);
		}
		return null; 
	}
	
}
