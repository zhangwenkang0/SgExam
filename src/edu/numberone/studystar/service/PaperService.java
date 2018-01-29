package edu.numberone.studystar.service;

import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Paper;
import edu.numberone.studystar.entity.Scores;
import edu.numberone.studystar.entity.SubAnswer;

public interface PaperService {
	public Paper findPaperById(String pid);
	
	//Ä³Ò»Ò³µÄ¿¼ÊÔ
	public Pager<Paper> findAllPaper(Integer currentPage, Integer pageSize,String c_id,String keyword);
	
	public Pager<Paper> findPaperByClassesId(Integer currentPage, Integer pageSize,String classesId);
	
	public void saveSubAnswer(SubAnswer answer);
	
	public void saveScore(String sc_id,String pid,String sid,Float score,String cid,String pname,String sname,String cls_id,String cls_name);
}
