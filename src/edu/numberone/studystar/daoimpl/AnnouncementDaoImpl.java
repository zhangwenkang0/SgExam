package edu.numberone.studystar.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import edu.numberone.studystar.entity.Announcement;
import edu.numberone.studystar.utils.DBUtils;

public class AnnouncementDaoImpl {

	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	
	/*
	 * 方法名:getAnouncemnet
	 * 参数:	announcement Annoucement对象	决定选择条件
	 * 		page 整形变量	参数大于0表示查询的第几页，参数小于0表示查询全部结果
	 * 		number 整形变量	当页数大于0时表示返回结果数量
	 * 返回值:	LinkedList<Announcement>类型的序列
	 * */
	
	public LinkedList<Announcement> getAnnouncement(Announcement announcement,int page, int number){
		LinkedList<Announcement> anns = new LinkedList<Announcement>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("Select * from announcement where 1 = 1");
		
		if(announcement.getA_id() != null){
			sql.append(" and a_id = '" + announcement.getA_id() + "'");
		}
		
		if(announcement.getA_title() != null){
			sql.append(" and a_title = '"  + announcement.getA_title() + "'");
		}
		
		if(announcement.getA_content() != null){
			sql.append(" and a_content = '" + announcement.getA_content() + "'");
		}
		
		if(announcement.getA_author() != null){
			sql.append(" and a_author = '" + announcement.getA_author() + "'");
		}
		
		if(announcement.getA_startTime() != null){
			//String startTime="to_date("+ announcement.getA_startTime()+",'yyyy-MM-dd')";
			sql.append(" and a_startTime <= '" + announcement.getA_startTime()+"'");
		}
		
		if(announcement.getA_endTime() != null){
			//String endTime="to_date("+ announcement.getA_endTime()+",'yyyy-MM-dd')";
			sql.append(" and a_endTime>= '"+announcement.getA_endTime()+"'");
		}
		
		if(number>0){
			sql.append(" limit " + page + ", " + number);
		}
		try{
			conn = DBUtils.getInstance().openConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			Announcement ann;
			while(rs.next()){
				ann = new Announcement();
				ann.setA_id(rs.getString("a_id"));
				ann.setA_title(rs.getString("a_title"));
				ann.setA_content(rs.getString("a_content"));
				ann.setA_author(rs.getString("a_author"));
				ann.setA_startTime(rs.getString("a_startTime"));
				ann.setA_endTime(rs.getString("a_endTime"));
				ann.setA_createDate(rs.getString("a_createdate"));
				anns.add(ann);
			}
		}catch(Exception e){
			e.printStackTrace();
			anns = null;
		}finally{
			resouseClose();
		}
		return anns;
	}
	
	/*
	 *方法名:deleteAnnoucement
	 *参数:	announcement Annoucement对象	决定选择条件
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String deleteAnnoucement(Announcement announcement){
		String resultStr = "";
		int strCount = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from announcement where 1 = 1");
		
		if(announcement.getA_id() != null){
			sql.append(" and a_id = '" + announcement.getA_id() + "'");
			strCount++;
		}
		
		if(announcement.getA_author() != null){
			sql.append(" and a_author = '" + announcement.getA_author() + "'");
			strCount++;
		}
		
		if(strCount == 0){
			resultStr = "CANNOT_DELETE_ALL_ANNOUNCEMENT";
		}else{
			try{
				conn = DBUtils.getInstance().openConnection();
				st = conn.createStatement();
				if(st.executeUpdate(sql.toString()) > 0){
					resultStr = "DELETE_ANNOUNCEMENT_SUCCESS";
				}else{
					resultStr = "DELETE_ANNOUNCEMENT_FAILURE";
				}
				
			}catch(Exception e){
				e.printStackTrace();
				
			}finally{
				resouseClose();
			}
		}
		
		return resultStr;
	}
	
	/*
	 *方法名:updateAnnoucement	更新符合条件的数据
	 *参数:	announcement Annoucement对象	决定选择条件
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String updateAnnouncement(Announcement announcement){
		String resultStr = "";
		String sql = "update announcement set a_title = ?, a_content = ?, a_author=?,a_starttime = ?,a_endtime=? where a_id = ?";
		
		if(announcement.getA_id() == null){
			resultStr = "CANNOT_UPDATE_ANNOUNCEMENT_WITH_NULL_ID";
		}else{
			try{
				conn = DBUtils.getInstance().openConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, announcement.getA_title());
				ps.setString(2, announcement.getA_content());
				ps.setString(3, announcement.getA_author());
				ps.setString(4, announcement.getA_startTime());
				ps.setString(5, announcement.getA_endTime());
				ps.setString(6, announcement.getA_id());
				int result = ps.executeUpdate();
				if(result == 1){
					resultStr = "UPDATE_ANNOUCEMENT_SUCCESS";
				}else if(result == 0){
					resultStr = "UPDATE_ANNOUCEMENT_FAILURE";
				}
				
			}catch(Exception e){
				e.printStackTrace();
				
			}finally{
				resouseClose();
			}
		}
		
		return resultStr;
	}
	
	/*
	 *方法名:addAnnoucement	更新符合条件的数据
	 *参数:	announcement Annoucement对象	添加对象
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String addAnnouncement(Announcement announcement){
		String resultStr = "";
		String sql = "insert into announcement (a_title, a_content, a_author,a_starttime,a_endtime) values(?, ?, ?, ?,?)";
		
		try{
			conn = DBUtils.getInstance().openConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, announcement.getA_title());
			ps.setString(2, announcement.getA_content());
			ps.setString(3, announcement.getA_author());
			ps.setString(4, announcement.getA_startTime());
			ps.setString(5, announcement.getA_endTime());
			int result = ps.executeUpdate();
			if(result == 1){
				resultStr = "ADD_ANNOUCEMENT_SUCCESS";
			}else if(result == 0){
				resultStr = "ADD_ANNOUCEMENT_FAILURE";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			resouseClose();
		}
		
		return resultStr;
	}
	
	
	private void resouseClose(){
		try{
			if(rs != null){
				rs.close();
			}
			if(st != null){
				st.close();
			}
			if(ps != null){
				ps.close();
			}
			if(conn != null){
				conn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
