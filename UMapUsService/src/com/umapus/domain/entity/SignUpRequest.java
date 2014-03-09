package com.umapus.domain.entity;

public class SignUpRequest {

	
	 private String familyName;
	 private String firstName;
	 private String lastName;
	 private String email;
	 private String password;
	 
	 
	 public String getFamilyName(){
		 
		 return this.familyName;
	 }
	 
	 public void setFamilyName(String familyName){
		 this.familyName = familyName;
	 }
	 
	 
    public String getFirstName(){
		 
		 return this.firstName;
	 }
	 
	 public void setFirstName(String firstName){
		 this.firstName = firstName;
	 }
	 
	 
    public String getLastName(){
		 
		 return this.lastName;
	 }
	 
	 public void setLastName(String lastName){
		 this.lastName = lastName;
	 }
	 
    public String getEmail(){
		 
		 return this.email;
	 }
	 
	 public void setEmail(String email){
		 this.email = email;
	 }
	 
    public String getPassWord(){
		 
		 return this.password;
	 }
	 
	 public void setPassWord(String password){
		 this.password = password;
	 }
}
