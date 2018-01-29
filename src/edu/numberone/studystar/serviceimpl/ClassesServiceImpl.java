package edu.numberone.studystar.serviceimpl;

import java.util.List;

import edu.numberone.studystar.entity.Classes;
import edu.numberone.studystar.jdbc.JdbcQuery;
import edu.numberone.studystar.jdbc.JdbcUtils;
import edu.numberone.studystar.service.ClassesService;

public class ClassesServiceImpl implements ClassesService{

	@Override
	public List<Classes> findAllClasses() {
		// TODO Auto-generated method stub
		String sql="select * from classes";
		JdbcQuery querys = JdbcUtils.createNativeQuery( sql, Classes.class);
		List<Classes> classesList=(List<Classes>) querys.getResultList();
		return classesList;
	}

	@Override
	public Classes findClasses(String cls_id) {
		// TODO Auto-generated method stub
		String sql="select * from classes where cls_id='"+cls_id+"'";
		JdbcQuery querys = JdbcUtils.createNativeQuery( sql, Classes.class);
		Classes classes=querys.getBean();
		return classes;
	}

}
