package com.umapus.domain.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;

import com.umapus.domain.entity.LoginRequest;
import com.umapus.domain.entity.SignUpRequest;

public class UMapUsMapper {

  
	@Autowired
	private SignUpRequest signUpRequest;
	
	@Autowired
	private LoginRequest loginRequest;
	
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
}
