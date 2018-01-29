package edu.numberone.studystar.serviceimpl;

import java.util.List;

import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.entity.QuestionType;
import edu.numberone.studystar.entity.Scores;
import edu.numberone.studystar.entity.SubAnswer;
import edu.numberone.studystar.jdbc.JdbcQuery;
import edu.numberone.studystar.jdbc.JdbcUtils;
import edu.numberone.studystar.service.PagerService;
import edu.numberone.studystar.service.ScoreService;

public class ScoreServiceImpl implements ScoreService {

	@Override
	public Pager<Scores> findAllScore(Integer currentPage, Integer pageSize, String c_id, String cls_id,
			String keyword) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from scores where 1=1");
		if (!"0".equals(c_id)&&null!=c_id) {
			sql.append(" and c_id='" + c_id+"'");
		}
		if (!"0".equals(cls_id)&&null!=cls_id) {
			sql.append(" and cls_id='" + cls_id+"'");
		}
		if (!"".equals(keyword)&&null!=keyword) {
			sql.append(" and p_name like"+" '%" +keyword+"%'");
		}
		PagerService<Scores> score=new PagerServiceImpl<>();
		Pager<Scores> pager=score.findFromModel(currentPage, pageSize, sql,Scores.class);
		return pager;
	}

	@Override
	public List<SubAnswer> findSubAnswer(String sc_id) {
		// TODO Auto-generated method stub
		String sql="select * from q5answer where sc_id='"+sc_id+"'";
		JdbcQuery query=JdbcUtils.createNativeQuery( sql, SubAnswer.class);
		List<SubAnswer> subAnswerList=(List<SubAnswer>) query.getResultList();
		return subAnswerList;
	}

	@Override
	public void correctSubAnswer(String sc_id, Float subScore) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("update scores set sc_score=sc_score+"+subScore+",");
		sql.append("ifdone='1'");
		sql.append(" where sc_id='"+sc_id+"'");
		JdbcQuery query=JdbcUtils.createNativeQuery( sql.toString(), SubAnswer.class);
		query.excuteUpdate();
	}

}
