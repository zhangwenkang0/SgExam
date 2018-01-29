package edu.numberone.studystar.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;

import edu.numberone.studystar.entity.*;
import edu.numberone.studystar.utils.DBUtils;

public class QuestionDaoImpl {
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	
	/*
	 * 方法名:getQuestion
	 * 参数:	question Question对象	决定选择条件
	 * 		page 整形变量	参数大于0表示查询的第几页，参数小于0表示查询全部结果
	 * 		number 整形变量	当页数大于0时表示返回结果数量
	 * 返回值:	LinkedList<Question>类型的序列
	 * */
	
	public LinkedList<Question> getQuestion(Question question, int page, int number){
		LinkedList<Question> ques = new LinkedList<Question>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("Select * from question where 1 = 1");
		
		if(question.getQ_id() != null){
			sql.append(" and q_id = '" + question.getQ_id() + "'");
		}
		
		if(question.getQ_title() != null){
			sql.append(" and q_title like '%"  + question.getQ_title() + "%'");
		}
		
		if(question.getQ_type() != null){
			sql.append(" and q_type = '" + question.getQ_type() + "'");
		}
		
		if(question.getQ_select() != null){
			sql.append(" and q_select like '%" + question.getQ_select() + "%'");
		}
		
		if(question.getQ_score() != null){
			sql.append(" and a_date = '" + question.getQ_score() + "'");
		}
		
		if(number > 0){
			page = (page - 1) * number;
			sql.append(" limit " + page + ", " + number);
		}
		
		try{
			conn = DBUtils.getInstance().openConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			Question que;
			while(rs.next()){
				que = new Question();
				que.setQ_id(rs.getString("q_id"));
				que.setQ_title(rs.getString("q_title"));
				que.setQ_type(rs.getString("q_type"));
				que.setQ_select(rs.getString("q_select"));
				que.setQ_answer(rs.getString("q_answer"));
				que.setQ_score(rs.getString("q_score"));
				ques.add(que);
			}
		}catch(Exception e){
			e.printStackTrace();
			ques = null;
		}finally{
			resouseClose();
		}
		return ques;
	}
	
	/*
	 * 方法名:getQuestion
	 * 参数:	qList String 查找qList(题目编号串)中题目
	 * 		page 整形变量	参数大于0表示查询的第几页，参数小于0表示查询全部结果
	 * 		number 整形变量	当页数大于0时表示返回结果数量
	 * 返回值:	LinkedList<Question>类型的序列
	 * */
	
	public LinkedList<Question> getQuestion(String qList, int page, int number){
		LinkedList<Question> ques = new LinkedList<Question>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM question WHERE LOCATE(CONCAT('@', q_id, '@'), '"+ qList +"')");
		
		if(number > 0){
			page = (page - 1) * number;
			sql.append(" limit " + page + ", " + number);
		}
		
		try{
			conn = DBUtils.getInstance().openConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			Question que;
			while(rs.next()){
				que = new Question();
				que.setQ_id(rs.getString("q_id"));
				que.setQ_title(rs.getString("q_title"));
				que.setQ_type(rs.getString("q_type"));
				que.setQ_select(rs.getString("q_select"));
				que.setQ_answer(rs.getString("q_answer"));
				que.setQ_score(rs.getString("q_score"));
				ques.add(que);
			}
		}catch(Exception e){
			e.printStackTrace();
			ques = null;
		}finally{
			resouseClose();
		}
		return ques;
	}
	
	/*
	 *方法名:deleteQuestion
	 *参数:	question Question对象	决定选择条件
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String deleteQuestion(Question question){
		String resultStr = "";
		int strCount = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from question where 1 = 1");
		
		if(question.getQ_id() != null){
			sql.append(" and q_id = '" + question.getQ_id() + "'");
			strCount++;
		}
		
		
		if(strCount == 0){
			resultStr = "CANNOT_DELETE_ALL_QUESTION";
		}else{
			try{
				conn = DBUtils.getInstance().openConnection();
				st = conn.createStatement();
				if(st.executeUpdate(sql.toString()) > 0){
					resultStr = "DELETE_QUESTION_SUCCESS";
				}else{
					resultStr = "DELETE_QUESTION_FAILURE";
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
	 *方法名:updateQuestion	更新符合条件的数据
	 *参数:	question Question对象	决定选择条件
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String updateQuestion(Question question){
		String resultStr = "";
		String sql = "update question set q_type = ?, q_title = ?, q_select = ?, q_score = ?, q_answer = ? where q_id = ?";
		
		if(question.getQ_id() == null){
			resultStr = "CANNOT_UPDATE_ANNOUNCEMENT_WITH_NULL_ID";
		}else{
			try{
				conn = DBUtils.getInstance().openConnection();
				ps = conn.prepareStatement(sql);
				ps.setString(1, question.getQ_type());
				ps.setString(2, question.getQ_title());
				ps.setString(3, question.getQ_select());
				ps.setString(4, question.getQ_score());
				ps.setString(5, question.getQ_answer());
				ps.setString(6, question.getQ_id());
				int result = ps.executeUpdate();
				if(result == 1){
					resultStr = "UPDATE_QUESTION_SUCCESS";
				}else if(result == 0){
					resultStr = "UPDATE_QUESTION_FAILURE";
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
	 *方法名:addQusetion	更新符合条件的数据
	 *参数:	question Question对象	添加对象
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String addQuestion(Question question){
		String resultStr = "";
		String sql = "insert into question (q_type, q_title, q_select, q_score, q_answer) values(?, ?, ?, ?, ?)";
		
		try{
			conn = DBUtils.getInstance().openConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, question.getQ_type());
			ps.setString(2, question.getQ_title());
			ps.setString(3, question.getQ_select());
			ps.setString(4, question.getQ_score());
			ps.setString(5, question.getQ_answer());
			int result = ps.executeUpdate();
			if(result == 1){
				resultStr = "ADD_QUESTION_SUCCESS";
			}else if(result == 0){
				resultStr = "ADD_QUSETION_FAILURE";
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
