package edu.numberone.studystar.service;

import java.util.List;

import edu.numberone.studystar.entity.Manager;
import edu.numberone.studystar.entity.Pager;

public interface PersonInformationService <T>{
	
	
	/**
	 * 从model的Dao层获得个人信息
	 * @param person 需要查找的个人信息
	 * @return 返回已查找的个人信息 ，如果查找成功则返回个人信息，否则返回为null
	 * 
	 * */
	public T findPersonInformationFromModel( T person);
	
	
	
	/**
	 * 修改个人信息到model
	 * @param person 需要修改的个人信息
	 * @return 返回已修改的个人信息 ，如果修改成功则返回个人信息，否则返回为null
	 * 
	 * */
   public int updatePersonInformationToModel(T person);
   
   
   
   /**
	 *查找所有人员信息
	 * 
	 * */
   public Pager<T> findAllPersonInformationFromModel(int pageNum, int pageSize);
   
   
   /**
  	 *添加人员信息到model
  	 *@param person 需要添加person
  	 * 
  	 * */
   public int AddPersonInformationToModel(T person);
   
   /**
 	 *添加人员信息到model
 	 * @param persons 需要需要删除的person集合
 	 * */
   public int deletePersonInformationToMedel(List<T> persons);

}
