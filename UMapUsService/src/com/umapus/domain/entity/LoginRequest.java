package com.umapus.domain.entity;

public class LoginRequest {
	
	private String userName;
	private String passWord;
	
	public String getuserName(){
		return this.userName;
		
	}
	
	public void setUserName(String userName){
		this.userName = userName;
	}

	public String getPassWord(){
		return this.passWord;
	}
	
	public void setPassWord(String password){
		
		this.passWord = password;
	}
	
//	public LoginRequest(String userName,String passWord){
//		
//		this.setUserName(userName);
//		this.setPassWord(passWord);
//	}
}
