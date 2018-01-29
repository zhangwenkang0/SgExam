package edu.numberone.studystar.entity;


public class Paper {
	private String p_id = null;
	private String c_id = null;
	private String p_name = null;
	private String q_id = null;
	private String createTime=null;
	private String updateTime=null;
	private String createUser=null;
	private String updateUser=null;
	private String startTime=null;
	private String endTime=null;
	private Float p_scores=null;
	private String p_date=null;
	private String cls_id=null;
	
	
	public String getCls_id() {
		return cls_id;
	}

	public void setCls_id(String cls_id) {
		this.cls_id = cls_id;
	}

	public String getP_date() {
		return p_date;
	}

	public void setP_date(String p_date) {
		this.p_date = p_date;
	}


	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getUpdateUser() {
		return updateUser;
	}

	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	public Float getP_scores() {
		return p_scores;
	}

	public void setP_scores(Float score) {
		this.p_scores = score;
	}


	public void setP_id(String p_id) {
		this.p_id = p_id;
	}

	public void setC_id(String c_id) {
		this.c_id = c_id;
	}

	public void setP_name(String p_name) {
		this.p_name = p_name;
	}


	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}

	public String getP_id() {
		return p_id;
	}

	public String getC_id() {
		return c_id;
	}

	public String getP_name() {
		return p_name;
	}

	public String getQ_id() {
		return q_id;
	}

}
