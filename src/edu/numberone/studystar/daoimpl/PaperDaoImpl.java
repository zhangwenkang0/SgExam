package edu.numberone.studystar.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;

import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.utils.DBUtils;

public class PaperDaoImpl {
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	
	/*
	 * 方法名:getPapers
	 * 参数:	paper Paper对象	决定选择条件
	 * 		page 整形变量	参数大于0表示查询的第几页，参数小于0表示查询全部结果
	 * 		number 整形变量	当页数大于0时表示返回结果数量
	 * 返回值:	LinkedList<Paper>类型的序列
	 * */
	
	public LinkedList<Paper> getPaper(Paper paper,int page, int number){
		LinkedList<Paper> papers = new LinkedList<Paper>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("Select * from paper where 1 = 1");
		
		if(paper.getP_id() != null){
			sql.append(" and p_id = '" + paper.getP_id() + "'");
		}
		
		if(paper.getC_id() != null){
			sql.append(" and c_id = '"  + paper.getC_id() + "'");
		}
		
		if(paper.getP_name() != null){
			sql.append(" and p_name = '" + paper.getP_name() + "'");
		}
		
		if(paper.getQ_id() != null){
			sql.append(" and q_id = '" + paper.getQ_id() + "'");
		}
		
		
		if(paper.getCreateTime() != null){
			sql.append(" and createTime = '" + paper.getCreateTime() + "'");
		}
		
		if(paper.getUpdateTime() != null){
			sql.append(" and updateTime = '" + paper.getUpdateTime() + "'");
		}
		
		if(paper.getCreateUser() != null){
			sql.append(" and createUser = '" + paper.getCreateUser() + "'");
		}
		
		
		if(paper.getUpdateUser() != null){
			sql.append(" and updateUser = '" + paper.getUpdateUser() + "'");
		}
		
		if(paper.getEndTime() != null){
			sql.append(" and endTime = '" + paper.getEndTime() + "'");
		}
		
		if(paper.getStartTime() != null){
			sql.append(" and startTime = '" + paper.getStartTime() + "'");
		}
		
		if(number > 0){
			page = (page - 1) * number;
			sql.append(" limit " + page + ", " + number);
		}
		System.out.println("sql--->" + sql);
		try{
			conn = DBUtils.getInstance().openConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			Paper pap;
			SimpleDateFormat format =new SimpleDateFormat("yyyy-MM-dd HH:mm");
			while(rs.next()){
				pap = new Paper();
				pap.setP_id(rs.getString("p_id"));
				pap.setC_id(rs.getString("c_id"));
				pap.setP_name(rs.getString("p_name"));
				pap.setQ_id(rs.getString("q_id"));
				pap.setP_scores(rs.getFloat("p_scores"));
				pap.setCreateTime(format.format(rs.getDate("createTime")));
				pap.setUpdateTime(format.format(rs.getDate("updateTime")));
				pap.setCreateUser(rs.getString("createUser"));
				pap.setUpdateUser(rs.getString("updateUser"));
				pap.setStartTime(format.format(rs.getDate("startTime")));
				pap.setEndTime(format.format(rs.getDate("endTime")));
				papers.add(pap);
			}
		}catch(Exception e){
			e.printStackTrace();
			papers = null;
		}finally{
			resouseClose();
		}
		return papers;
	}
	
	/*
	 *方法名:deletePaper
	 *参数:	paper Paper对象	决定选择条件
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String deletePaper(Paper paper){
		String resultStr = "";
		int strCount = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from paper where 1 = 1");
		
		if(paper.getP_id() != null){
			sql.append(" and p_id = '" + paper.getP_id() + "'");
			strCount++;
		}
		
		if(strCount == 0){
			resultStr = "CANNOT_DELETE_ALL_PAPER";
		}else{
			try{
				conn = DBUtils.getInstance().openConnection();
				st = conn.createStatement();
				if(st.executeUpdate(sql.toString()) > 0){
					resultStr = "DELETE_Paper_SUCCESS";
				}else{
					resultStr = "DELETE_PAPER_FAILURE";
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
	 *方法名:updatePaper	更新符合条件的数据
	 *参数:	paper Paper对象	决定选择条件
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String updatePaper(Paper paper){
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		String resultStr = "";
		String sql = "update paper set c_id = ?, p_name = ?,  q_id = ?, p_scores = ?,"
				+ "updateTime=?,updateUser=?,startTime=?,endTime=? ,cls_id=? where p_id = ?";
		
		if(paper.getP_id() == null){
			resultStr = "CANNOT_UPDATE_PAPER_WITH_NULL_ID";
		}else{
			try{
				conn = DBUtils.getInstance().openConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, paper.getC_id());
				ps.setString(2, paper.getP_name());
				ps.setString(3, paper.getQ_id());
				ps.setFloat(4, paper.getP_scores());
				ts=Timestamp.valueOf(paper.getUpdateTime());   
				ps.setTimestamp(5, ts);
				ps.setString(6, paper.getUpdateUser());
				ts=Timestamp.valueOf(paper.getStartTime());
				ps.setTimestamp(7,ts);
				ts=Timestamp.valueOf(paper.getEndTime());  
				ps.setTimestamp(8, ts);
				ps.setString(10, paper.getP_id());
				ps.setString(9, paper.getCls_id());
				int result = ps.executeUpdate();
				if(result == 1){
					resultStr = "UPDATE_PAPER_SUCCESS";
				}else if(result == 0){
					resultStr = "UPDATE_PAPER_FAILURE";
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
	 *方法名:addPaper	更新符合条件的数据
	 *参数:	paper Paper对象	添加对象
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String addPaper(Paper paper){
		String resultStr = "";
		String sql = "insert into paper (c_id, p_name,q_id, p_scores,createUser,startTime,endTime,p_id,cls_id) values( ?,?, ?, ?, ?,?, ?,?,?)";
		Timestamp ts = new Timestamp(System.currentTimeMillis());   
		try{
			conn = DBUtils.getInstance().openConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, paper.getC_id());
			ps.setString(2, paper.getP_name());
			ps.setString(3, paper.getQ_id());
			ps.setFloat(4, paper.getP_scores());
			ps.setString(5, paper.getCreateUser());
			ts=Timestamp.valueOf(paper.getStartTime());   
			ps.setTimestamp(6, ts);
			ts=Timestamp.valueOf(paper.getEndTime());
			ps.setTimestamp(7, ts);
			ps.setString(8, paper.getP_id());
			ps.setString(9, paper.getCls_id());
			int result = ps.executeUpdate();
			if(result == 1){
				resultStr = "ADD_PAPER_SUCCESS";
			}else if(result == 0){
				resultStr = "ADD_PAPER_FAILURE";
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
