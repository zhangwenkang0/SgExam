package edu.numberone.studystar.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import edu.numberone.studystar.entity.*;
import edu.numberone.studystar.utils.DBUtils;

public class ScoresDaoImpl {

	public static void main(String[] args) {
		ScoresDaoImpl sdi = new ScoresDaoImpl();
		String p_id="2";
		System.out.println("考试总人数："+sdi.selectNumber(p_id));
		Scores sc = new Scores();
		
		sc.setSc_score(60f);   //及格分数
		sc.setP_id(p_id);
		
		LinkedList list = sdi.getScores(sc, 1,10);
		System.out.println("及格人数："+list.size());
		
//		int sum_score=0;
		float passPercent;
		for(int i=0; i<list.size();++i){
			System.out.println(((Scores) list.get(i)).getSc_score());
		}
		
//		for(int j=0; j<list.size();++j){
//			int tempscore = Integer.parseInt(((Scores) list.get(j)).getSc_score());
//			sum_score +=tempscore;
//		}
//		System.out.println("及格总分数:"+sum_score);
		passPercent = (float)list.size()/(sdi.selectNumber(p_id))*100;
		float pass = (float)(Math.round(passPercent *100))/100;
		System.out.println("及格率:"+pass+"%"); //保留两位小数输出
//		System.out.println(sdi.getScores(sc, 1, 2).get(0).getSc_score());
	}
	
	private Connection conn = null;
	private Statement st = null;
	private ResultSet rs = null;
	private PreparedStatement ps = null;
	
	
	/*
	 * 方法名:getScores
	 * 参数:	scores Scores对象	决定选择条件
	 * 		page 整形变量	参数大于0表示查询的第几页，参数小于0表示查询全部结果
	 * 		number 整形变量	当页数大于0时表示返回结果数量
	 * 返回值:	LinkedList<Scores>类型的序列
	 * */
	
	public LinkedList<Scores> getScores(Scores scores, int page, int number){
		LinkedList<Scores> scos = new LinkedList<Scores>();
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM scores WHERE s_id = '"+scores.getS_id()+"'");
		
		if(scores.getSc_id() != null){
			sql.append(" and sc_id = '" + scores.getSc_id() + "'");
		}
		
		if(scores.getP_id() != null){
			sql.append(" and scores.`P_ID` = '"  + scores.getP_id() + "'");
		}
		
		if(scores.getS_id() != null){
			sql.append(" and s_id = '" + scores.getS_id() + "'");
		}
		
		
		if(scores.getSc_score() != null){
			sql.append(" and sc_score >= '" + scores.getSc_score() + "'");
		}
		
		if(scores.getIfdone() != null){
			sql.append(" and ifdone >= '" + scores.getIfdone()  + "'");
		}
		
		if(scores.getP_name()!= null){
			sql.append(" and p_name >= '" + scores.getP_name()  + "'");
		}
		
		if(scores.getSc_id()!= null){
			sql.append(" and c_id >= '" + scores.getSc_id()  + "'");
		}
		
		if(number > 0){
			page = (page - 1) * number;
			sql.append(" limit " + page + ", " + number);
		}
		
		try{
			conn = DBUtils.getInstance().openConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			Scores sco;
			while(rs.next()){
				sco = new Scores();
				sco.setSc_id(rs.getString("sc_id"));
				sco.setP_id(rs.getString("p_id"));
				sco.setS_id(rs.getString("s_id"));
				sco.setC_id(rs.getString("c_id"));
				sco.setSc_score(rs.getFloat("sc_score"));
				sco.setSc_date(rs.getString("sc_date"));
				sco.setP_name(rs.getString("P_Name"));
				sco.setIfdone(rs.getString("ifdone"));
				sco.setS_name(rs.getString("s_name"));
				scos.add(sco);
			}
		}catch(Exception e){
			e.printStackTrace();
			scos = null;
		}finally{
			resouseClose();
		}
		return scos;
	}
	
	/*
	 *方法名:deleteScores
	 *参数:	scores Scores对象	决定选择条件
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String deleteScores(Scores scores){
		String resultStr = "";
		int strCount = 0;
		StringBuilder sql = new StringBuilder();
		sql.append("delete from scores where 1 = 1");
		
		if(scores.getSc_id() != null){
			sql.append(" and sc_id = '" + scores.getSc_id() + "'");
			strCount++;
		}
		
		
		if(strCount == 0){
			resultStr = "CANNOT_DELETE_ALL_SCORES";
		}else{
			try{
				conn = DBUtils.getInstance().openConnection();
				st = conn.createStatement();
				if(st.executeUpdate(sql.toString()) > 0){
					resultStr = "DELETE_SCORES_SUCCESS";
				}else{
					resultStr = "DELETE_SCORES_FAILURE";
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
	 *方法名:addScores	更新符合条件的数据
	 *参数:	scores Scores对象	添加对象
	 *返回值:	String类型字符串,表示查询结果
	 * */
	public String addScores(Scores scores){
		String resultStr = "";
		String sql = "insert into scores (p_id, s_id, sc_score, sc_date) values(?,  ?, ?, now())";
		
		try{
			conn = DBUtils.getInstance().openConnection();
			ps = conn.prepareStatement(sql);
			ps.setString(1, scores.getP_id());
			ps.setString(2, scores.getS_id());
			ps.setFloat(3, scores.getSc_score());
			int result = ps.executeUpdate();
			if(result == 1){
				resultStr = "ADD_SCORES_SUCCESS";
			}else if(result == 0){
				resultStr = "ADD_SCORES_FAILURE";
			}
			
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			resouseClose();
		}
		
		return resultStr;
	}
	
	/***
	 * 方法名：selectNumber  查询某一次考试的考试人数
	 * @param p_id  考试的id号
	 * @return 返回参加该次考试的人数
	 */
	public int selectNumber(String p_id){
		StringBuilder sql = new StringBuilder();
		 sql.append("SELECT  count(*) from  scores");
		 sql.append(" where p_id= '"+p_id +" '");
		 
		 System.out.println(sql.toString());
		conn = DBUtils.getInstance().openConnection();
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql.toString());
			
			if(rs.next()){
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
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
