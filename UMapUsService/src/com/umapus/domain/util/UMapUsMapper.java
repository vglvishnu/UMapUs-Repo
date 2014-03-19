package com.umapus.domain.util;

import javax.naming.directory.Attributes;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;

import com.umapus.domain.entity.LoginRequest;
import com.umapus.domain.entity.SignUpRequest;
import com.umapus.domain.entity.User;

public class UMapUsMapper {

  
	@Autowired
	private SignUpRequest signUpRequest;
	
	@Autowired
	private LoginRequest loginRequest;
	
	@Autowired 
	User user;
	
	public LoginRequest MapJsonToLoginRequest(String jsonLoginRequest) throws JSONException{
	  
	   JSONObject ojson = (JSONObject) new JSONTokener(jsonLoginRequest).nextValue();
	   loginRequest.setUserName(ojson.getString("email"));
	   loginRequest.setPassWord(ojson.getString("passwd"));
	   return loginRequest;
	   
   }
   
   public SignUpRequest MapJsonToSignupRequest(String jsonSignUpRequest) throws JSONException{
	   
	
	    JSONObject ojson = (JSONObject) new JSONTokener(jsonSignUpRequest).nextValue();
	    
	    signUpRequest.setEmail(ojson.getString("email"));
	    signUpRequest.setFamilyName(ojson.getString("familyname"));
	    signUpRequest.setFirstName(ojson.getString("firstname"));
	    signUpRequest.setLastName(ojson.getString("lastname"));
	    signUpRequest.setPassWord(ojson.getString("passwd"));
	    
	   return signUpRequest;
   }
   
   public User MapDNAttributesToUser(Attributes dnAttributes){
	   
	    user.setEmailId(dnAttributes.get("uid").toString());
	    user.setEmailId(dnAttributes.get("graphid").toString());
	    user.setEmailId(dnAttributes.get("sn").toString());
	    user.setLoggedin(true);
	   
	   return user;
   }
   
   public JSONObject MakeJsonLoginResponseFromUser(User user) throws JSONException{
	   JSONObject ojson = new JSONObject();
	   ojson.put("loginsuccess",user.isLoggedin());
	   
	   return ojson;
   }
}
