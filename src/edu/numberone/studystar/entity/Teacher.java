package edu.numberone.studystar.entity;

public class Teacher {
    private String t_Id = null;
	private String t_Pass = null;
	private String t_Name = null;
	private String coll_Id = null;
	private String t_Job = null;
	private String coll_Name = null;
	
	public String getColl_Name() {
		return coll_Name;
	}
	public void setColl_Name(String coll_Name) {
		this.coll_Name = coll_Name;
	}
	public void setT_Id(String t_Id) {
		this.t_Id = t_Id;
	}
	public void setT_Pass(String t_Pass) {
		this.t_Pass = t_Pass;
	}
	public void setT_Name(String t_Name) {
		this.t_Name = t_Name;
	}
	public void setColl_Id(String coll_Id) {
		this.coll_Id = coll_Id;
	}
	public void setT_Job(String t_Job) {
		this.t_Job = t_Job;
	}
	public String getT_Id() {
		return t_Id;
	}
	public String getT_Pass() {
		return t_Pass;
	}
	public String getT_Name() {
		return t_Name;
	}
	public String getColl_Id() {
		return coll_Id;
	}
	public String getT_Job() {
		return t_Job;
	}
	
	
}
