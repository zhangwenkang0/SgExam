package edu.numberone.studystar.dao;

import java.util.List;

import edu.numberone.studystar.entity.Question;

/**
 * 试题管理，主要用于管理试题的增删改查
 * 
 * */
public interface QuestionDao {
	
	
	/**
	 * 查询试题
	 * @param sql 需要查询的语句
	 * @param params需要查询的参数
	 * @return 返回questtion的集合
	 * 
	 * */
	public List<Question>  findQuestionsFromDB(String sql ,String parms[]);
	
	
	
	/**
	 * 查询试题
	 * @param question 需要修改的试题
	 * @return 修改后的结果标识 ， 1代表成功，0代表失败
	 * 
	 * */
	public int updateQuestionToDB(Question question);
	
	
	/**
	 * 查询试题
	 * @param question 需要增加的试题
	 * @return 修改后的结果标识 ， 1代表成功，0代表失败
	 * 
	 * */
	public int addQuestionToDB(Question question);
	
	
	
	/**
	 * 查询试题
	 * @param questions 需要删除的试题的集合
	 * @return 修改后的结果标识 ， 1代表成功，0代表失败
	 * 
	 * */
	public int deleteQuestionToDB(List<Question> questions);

}
