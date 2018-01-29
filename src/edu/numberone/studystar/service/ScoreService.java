package edu.numberone.studystar.service;

import java.util.List;

import edu.numberone.studystar.entity.Pager;
import edu.numberone.studystar.entity.Scores;
import edu.numberone.studystar.entity.SubAnswer;

public interface ScoreService {
	public Pager<Scores> findAllScore(Integer currentPage, Integer pageSize,String c_id,String cls_id,String keyword);
	
	public List<SubAnswer> findSubAnswer(String sc_id);
	
	public void correctSubAnswer(String sc_id,Float subScore);
}
