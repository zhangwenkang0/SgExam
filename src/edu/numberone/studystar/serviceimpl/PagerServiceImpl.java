package edu.numberone.studystar.serviceimpl;

import java.util.List;

import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.jdbc.JdbcQuery;
import edu.numberone.studystar.jdbc.JdbcUtils;
import edu.numberone.studystar.service.PagerService;

public class PagerServiceImpl<T> implements PagerService<T>{

	@Override
	public Pager<T> findFromModel(int pageNum, int pageSize, StringBuffer sql,Class entity) {
		// TODO Auto-generated method stub
				JdbcQuery querys = JdbcUtils.createNativeQuery( sql.toString(), entity);
				List<T> list=(List<T>) querys.getResultList();
				if (list != null) {
					// ∑÷“≥≤È—Ø
					Pager<T> pager = new Pager<>(list,pageNum, pageSize);
					if (pager != null) {
						return pager;
					}
				}
				return null;
			
	}

	
}
