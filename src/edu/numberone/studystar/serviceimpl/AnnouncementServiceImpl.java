package edu.numberone.studystar.serviceimpl;

import edu.numberone.studystar.entity.Announcement;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.jdbc.JdbcQuery;
import edu.numberone.studystar.jdbc.JdbcUtils;
import edu.numberone.studystar.service.AnnouncementService;
import edu.numberone.studystar.service.PagerService;

public class AnnouncementServiceImpl implements AnnouncementService {

	@Override
	public Announcement findNewById(String ann_id) {
		// TODO Auto-generated method stub
		String sql="select * from announcement where a_id='"+ann_id+"'";
		JdbcQuery querys = JdbcUtils.createNativeQuery( sql, Announcement.class);
		Announcement ann=querys.getBean();
		return ann;
	}

	@Override
	public Pager<Announcement> findAllAnnouncement(Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("select * from announcement order by a_startTime desc");
		PagerService<Announcement> page=new PagerServiceImpl<>();
		Pager<Announcement> pager=page.findFromModel(currentPage, pageSize, sql,Announcement.class);
		return pager;
	}
	


}
