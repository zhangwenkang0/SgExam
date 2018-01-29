package edu.numberone.studystar.service;

import java.util.List;

import edu.numberone.studystar.entity.Count;
import edu.numberone.studystar.entity.Pager;

public interface CountService {
	public List<Count> claculateCount();
	public void insertCount(Count count);
	public void updateCount(Count count);
	public boolean ifHaveRecore(String p_id);
	public Pager<Count> findAllCount(Integer currentPage, Integer pageSize,String c_id,String cls_id,String keyword);
}
