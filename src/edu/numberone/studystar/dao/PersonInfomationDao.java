package edu.numberone.studystar.dao;

import java.util.List;
import java.util.Map;

import edu.numberone.studystar.entity.Manager;

public interface PersonInfomationDao<T> {
	
	
	
	/**
	 * 从数据库获得个人信息
	 * @param person 需要查找的个人信息
	 * @return 返回已查找的个人信息 ，如果查找成功则返回个人信息，否则返回为null
	 * 
	 * */
	public  T  findPersonInforFromDB(T person); 
	
	
	
	/**
	 * 从数据库获得个人信息
	 * @param sql 查找个人信息的sql语句
	 * @param 查找条件
	 * @return 返回已查找的个人信息 ，如果查找成功则返回个人信息，否则返回为null
	 * 
	 * */
	public  List<T>  findPersonInforFromDB(String sql ,String []parms); 
	
	
	/**
	 * 修改管理员的信息
	 * @param manager 需要修改的管理员
	 * 
	 * */
	public int updatepersonInformationToDB(T person);
		// TODO Auto-generated method stub
	
	/**
	 * 添加人员
	 * @param person 需要修改的管理员
	 * 
	 * */
	public int addPersonInformationToDB(T person);
		// TODO Auto-generated method stub
	
	
	/**
	 * 删除人员信息
	 * @param persons 需要删除人员的集合
	 * 
	 * */
	public int deletePersonInformationToDB(List<T> persons);
		// TODO Auto-generated method stub
	
	

}
