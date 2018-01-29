package edu.numberone.studystar.service;

import java.util.List;

import edu.numberone.studystar.entity.Classes;

public interface ClassesService {
	public List<Classes> findAllClasses();
	
	public Classes findClasses(String cls_id);
}
