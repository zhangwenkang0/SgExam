package edu.numberone.studystar.entity;

public class Student {
	private String s_id ;
	private String s_pass ;
	private String s_name ;
	private String s_sex ;
	private String cls_Name;
	private String cls_Id;

	public String getCls_Id() {
		return cls_Id;
	}
	public void setCls_Id(String cls_Id) {
		this.cls_Id = cls_Id;
	}
	public String getCls_Name() {
		return cls_Name;
	}
	public void setCls_Name(String cls_Name) {
		this.cls_Name = cls_Name;
	}
	public String getS_id() {
		return s_id;
	}
	public String getS_pass() {
		return s_pass;
	}
	public String getS_name() {
		return s_name;
	}
	public String getS_sex() {
		return s_sex;
	}
	public void setS_id(String s_id) {
		this.s_id = s_id;
	}
	public void setS_pass(String s_pass) {
		this.s_pass = s_pass;
	}
	public void setS_name(String s_name) {
		this.s_name = s_name;
	}
	public void setS_sex(String s_sex) {
		this.s_sex = s_sex;
	}
	
}
