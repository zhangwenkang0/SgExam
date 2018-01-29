package edu.numberone.studystar.serviceimpl;

import java.util.List;

import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.entity.Question;
import edu.numberone.studystar.entity.Scores;
import edu.numberone.studystar.entity.SubAnswer;
import edu.numberone.studystar.jdbc.JdbcQuery;
import edu.numberone.studystar.jdbc.JdbcUtils;
import edu.numberone.studystar.service.PagerService;
import edu.numberone.studystar.service.PaperService;
import edu.numberone.studystar.utils.UUIDUtils;

public class PaperServiceImpl implements PaperService {

	@Override
	public Paper findPaperById(String pid) {
		// TODO Auto-generated method stub
		String sql="select * from paper where p_id='"+pid+"'";
		JdbcQuery querys = JdbcUtils.createNativeQuery( sql, Paper.class);
		Paper paper=querys.getBean();
		String[] str=paper.getStartTime().split(" ");
		String date=str[0];
		paper.setP_date(date);
		paper.setStartTime(str[1].substring(0, 5));
		paper.setEndTime(paper.getEndTime().split(" ")[1].substring(0, 5));
		return paper;
	}

	@Override
	public Pager<Paper> findAllPaper(Integer currentPage, Integer pageSize,String c_id,String keyword) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("select * from paper where 1=1");
		if (!"0".equals(c_id)&&null!=c_id) {
			sql.append(" and c_id='" + c_id+"'");
		}
		if (!"".equals(keyword)&&null!=keyword) {
			sql.append(" and p_name like"+" '%" +keyword+"%'");
		}
		PagerService<Paper> page=new PagerServiceImpl<>();
		Pager<Paper> pager=page.findFromModel(currentPage, pageSize, sql,Paper.class);
		return pager;
	}

	@Override
	public Pager<Paper> findPaperByClassesId(Integer currentPage, Integer pageSize,String classesId) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("select * from paper where cls_id='"+classesId+"'"+"order by startTime");
		PagerService<Paper> page=new PagerServiceImpl<>();
		Pager<Paper> pager=page.findFromModel(currentPage, pageSize, sql,Paper.class);
		return pager;
	}

	@Override
	public void saveSubAnswer(SubAnswer answer) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("insert into q5answer(sc_id,q_id,q_title,s_id,answer,rightanswer,q_score) values(");
		sql.append("'"+answer.getSc_id()+"',");
		sql.append("'"+answer.getQ_id()+"',");
		sql.append("'"+answer.getQ_title()+"',");
		sql.append("'"+answer.getS_id()+"',");
		sql.append("'"+answer.getAnswer()+"',");
		sql.append("'"+answer.getRightAnswer()+"',");
		sql.append(""+answer.getQ_score()+")");
		JdbcQuery querys = JdbcUtils.createNativeQuery(sql.toString(), SubAnswer.class);
		querys.excuteUpdate();
	}

	@Override
	public void saveScore(String sc_id,String pid,String sid,Float score,String cid,String pname,String sname,
			String cls_id,String cls_name) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("insert into scores(sc_id,p_id,s_id,sc_score,c_id,p_name,s_name,cls_id,cls_name) values(");
		sql.append("'"+sc_id+"',");
		sql.append("'"+pid+"',");
		sql.append("'"+sid+"',");
		sql.append(""+score+",");
		sql.append("'"+cid+"',");
		sql.append("'"+pname+"',");
		sql.append("'"+sname+"',");
		sql.append("'"+cls_id+"',");
		sql.append("'"+cls_name+"')");
		JdbcQuery querys = JdbcUtils.createNativeQuery(sql.toString(), SubAnswer.class);
		querys.excuteUpdate();
	}

}
