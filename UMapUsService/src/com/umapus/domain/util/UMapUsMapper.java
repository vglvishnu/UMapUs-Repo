package com.umapus.domain.util;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.umapus.domain.entity.LoginRequest;
import com.umapus.domain.entity.SignUpRequest;

public class UMapUsMapper {

   public LoginRequest MapJsonToLoginRequest(String jsonLoginRequest) throws JSONException{
	  
	   JSONObject ojson = (JSONObject) new JSONTokener(jsonLoginRequest).nextValue();
	   return new LoginRequest(ojson.getString("email"),ojson.getString("passwd"));
	   
   }
   
   public SignUpRequest MapJsonToSignupRequest(String jsonSignUpRequest) throws JSONException{
	   
	    SignUpRequest signUpRequest = new SignUpRequest();
	    JSONObject ojson = (JSONObject) new JSONTokener(jsonSignUpRequest).nextValue();
	    
	    signUpRequest.setEmail(ojson.getString("email"));
	    signUpRequest.setFamilyName(ojson.getString("familyname"));
	    signUpRequest.setFirstName(ojson.getString("firstname"));
	    signUpRequest.setLastName(ojson.getString("lastname"));
	    signUpRequest.setPassWord(ojson.getString("passwd"));
	    
	   return signUpRequest;
   }
}
