package edu.numberone.studystar.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import edu.numberone.studystar.entity.Count;
import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Scores;
import edu.numberone.studystar.jdbc.JdbcQuery;
import edu.numberone.studystar.jdbc.JdbcUtils;
import edu.numberone.studystar.service.CountService;
import edu.numberone.studystar.service.PagerService;

public class CountServiceImpl implements CountService {

	@Override
	public List<Count> claculateCount() {
		// TODO Auto-generated method stub
		String sql1="select p_id from scores";
		JdbcQuery query1=JdbcUtils.createNativeQuery(sql1);
		List<String> pidList=(List<String>) query1.getStringList();
		StringBuffer pids=new StringBuffer("(");
		for(int i=0;i<pidList.size();i++){
			if(i!=pidList.size()-1){
				pids.append("'"+pidList.get(i)+"',");
			}else{
				pids.append("'"+pidList.get(i)+"')");
			}
		}
		StringBuffer sql2=new StringBuffer();
		sql2.append(" SELECT p_id,p_name,COUNT(*) allpeople,");
		sql2.append(" SUM(CASE WHEN sc_score>=60 THEN 1 ELSE 0 END) pass,");
		sql2.append(" SUM(CASE WHEN sc_score<60 THEN 1 ELSE 0 END) nopass");
		sql2.append(" FROM scores  WHERE ifdone='1' AND p_id IN "+pids+" GROUP BY p_id,p_name");
		JdbcQuery query2=JdbcUtils.createNativeQuery(sql2.toString(), Count.class);
		List<Count> countList=(List<Count>) query2.getResultList();
		return countList;
	}

	@Override
	public void insertCount(Count count) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("insert into count (p_id,p_name,allpeople,nopass,pass)  values (");
		sql.append("'"+count.getP_id()+"',");
		sql.append("'"+count.getP_name()+"',");
		sql.append(count.getAllPeople()+",");
		sql.append(count.getNopass()+",");
		sql.append(count.getPass()+")");
		JdbcQuery query=JdbcUtils.createNativeQuery(sql.toString(), Count.class);
		query.excuteUpdate();
		
	}

	@Override
	public void updateCount(Count count) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append(" update count set ");
		sql.append(" p_name=N'"+count.getP_name()+"',");
		sql.append(" allpeople="+count.getAllPeople()+",");
		sql.append(" nopass="+count.getNopass()+",");
		sql.append(" pass="+count.getPass());
		sql.append(" where p_id='"+count.getP_id()+"'");
		JdbcQuery query=JdbcUtils.createNativeQuery(sql.toString(), Count.class);
		query.excuteUpdate();
	}

	@Override
	public boolean ifHaveRecore(String p_id) {
		// TODO Auto-generated method stub
		String sql="select count(p_id) from count where p_id='"+p_id+"'";
		JdbcQuery query=JdbcUtils.createNativeQuery(sql, Count.class);
		int count=query.getCount();
		if(count>0){
			return true;
		}
		return false;
	}

	@Override
	public Pager<Count> findAllCount(Integer currentPage, Integer pageSize,String c_id,String cls_id,String keyword) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer();
		sql.append("select * from count where 1=1");
		if (!"0".equals(c_id)&&null!=c_id) {
			sql.append(" and c_id='" + c_id+"'");
		}
		if (!"0".equals(cls_id)&&null!=cls_id) {
			sql.append(" and cls_id='" + cls_id+"'");
		}
		if (!"".equals(keyword)&&null!=keyword) {
			sql.append(" and p_name like"+" '%" +keyword+"%'");
		}
		PagerService<Count> score=new PagerServiceImpl<>();
		Pager<Count> pager=score.findFromModel(currentPage, pageSize, sql,Count.class);
		return pager;
	}

}
