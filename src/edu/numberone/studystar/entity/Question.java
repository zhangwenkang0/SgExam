package edu.numberone.studystar.entity;

public class Question {
	private String q_id = null;
	private String q_type = null;
	private String q_title = null;
	private String q_select = null;
	private String q_score = null;
	private String q_answer = null;
	private String course_id=null;
	private String[] selects = null;
	private String q_explaination=null;
	
	

	
	public String[] getSelects() {
		return selects;
	}

	public void setSelects(String[] selects) {
		this.selects = selects;
	}

	public String getQ_explaination() {
		return q_explaination;
	}

	public void setQ_explaination(String q_explaination) {
		this.q_explaination = q_explaination;
	}

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	private void setSelects() {
		this.selects = this.q_select.split("@");
	}

	public void setQ_id(String q_id) {
		this.q_id = q_id;
	}

	public void setQ_type(String q_type) {
		this.q_type = q_type;
	}

	public void setQ_title(String q_title) {
		this.q_title = q_title;
	}

	public void setQ_select(String q_select) {
		this.q_select = q_select;
		if(this.q_select != null){
			setSelects();
		}
	}

	public void setQ_score(String q_score) {
		this.q_score = q_score;
	}

	public void setQ_answer(String q_answer) {
		this.q_answer = q_answer;
	}

	public String getQ_id() {
		return q_id;
	}

	public String getQ_type() {
		return q_type;
	}

	public String getQ_title() {
		return q_title;
	}

	public String getQ_select() {
		return q_select;
	}

	public String getQ_score() {
		return q_score;
	}

	public String getQ_answer() {
		return q_answer;
	}

}
