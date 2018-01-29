package edu.numberone.studystar.service;

import java.util.List;

import edu.numberone.studystar.dao.QuestionDao;
import edu.numberone.studystar.daoimpl.QuestionDaoImple;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.entity.QuestionType;
import edu.numberone.studystar.jdbc.JdbcQuery;
import edu.numberone.studystar.jdbc.JdbcUtils;

public interface PagerService<T> {
	public  Pager<T> findFromModel(int pageNum, int pageSize, StringBuffer sql,Class entity);
	}