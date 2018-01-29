package edu.numberone.studystar.service;

public interface PresonInformationFactoryService {
	
	
	
	/**
	 * 创建一个用于查看用户信息的工厂，用于根据不同的用户查看不同的信息
	 * @param personID 用户的id
	 * 
	 * 
	 * */
	public PersonInformationService<?> personFactory(String flag);

}
