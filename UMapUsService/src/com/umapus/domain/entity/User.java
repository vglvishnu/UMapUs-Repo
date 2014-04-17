package com.umapus.domain.entity;

public class User {

	private String emailId;
	private String graphId;
	private String Surname;
	private boolean loggedin;
	
	
	public boolean isLoggedin() {
		return loggedin;
	}
	public void setLoggedin(boolean loggedin) {
		this.loggedin = loggedin;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getGraphId() {
		return graphId;
	}
	public void setGraphId(String graphId) {
		this.graphId = graphId;
	}
	public String getSurname() {
		return Surname;
	}
	public void setSurname(String surname) {
		Surname = surname;
	}

}
