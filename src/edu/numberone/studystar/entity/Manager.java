package edu.numberone.studystar.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

public class Manager implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1574730664802026023L;
	private String m_id = null;
	private String m_pass = null;
	private String m_name = null;
	
	private Timestamp M_LastLoginTime;
	
	public Timestamp getM_LastLoginTime() {
		return M_LastLoginTime;
	}
	
	public void setM_LastLoginTime(Timestamp m_LastLoginTime) {
		M_LastLoginTime = m_LastLoginTime;
	}

	public void setM_id(String m_id) {
		this.m_id = m_id;
	}
	public void setM_pass(String m_pass) {
		this.m_pass = m_pass;
	}
	public void setM_name(String m_name) {
		this.m_name = m_name;
	}
	public String getM_id() {
		return m_id;
	}
	public String getM_pass() {
		return m_pass;
	}
	public String getM_name() {
		return m_name;
	}
	
	
	
}
