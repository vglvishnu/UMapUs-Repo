package com.umapus.domain.util;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;

import com.umapus.domain.entity.LoginRequest;
import com.umapus.domain.entity.SignUpRequest;
import com.umapus.domain.entity.UMapUsConstants;
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
	   loginRequest.setUserName(ojson.getString(UMapUsConstants.JsEMAIL));
	   loginRequest.setPassWord(ojson.getString(UMapUsConstants.JsPASSWD));
	   return loginRequest;
	   
   }
   
   public SignUpRequest MapJsonToSignupRequest(String jsonSignUpRequest) throws JSONException{
	   
	
	    JSONObject ojson = (JSONObject) new JSONTokener(jsonSignUpRequest).nextValue();
	    
	    signUpRequest.setEmail(ojson.getString(UMapUsConstants.JsEMAIL));
	    signUpRequest.setFamilyName(ojson.getString(UMapUsConstants.JsFAMILYNAME));
	    signUpRequest.setFirstName(ojson.getString(UMapUsConstants.JsFAMILYNAME));
	    signUpRequest.setLastName(ojson.getString(UMapUsConstants.JsLASTNAME));
	    signUpRequest.setPassWord(ojson.getString(UMapUsConstants.JsPASSWD));
	    
	   return signUpRequest;
   }
   
   public User MapDNAttributesToUser(Attributes dnAttributes) throws NamingException{
	   
	    //user.setEmailId(dnAttributes.get(UMapUsConstants.UID).toString());	
	    user.setEmailId(dnAttributes.get(UMapUsConstants.UID).get().toString());	
	    //dnAttributes.get(UMapUsConstants.UID).get().toString();
	    user.setGraphId(dnAttributes.get(UMapUsConstants.GRAPHID).get().toString());
	    user.setSurname(dnAttributes.get(UMapUsConstants.SN).get().toString());
	    user.setLoggedin(true);
	   
	   return user;
   }
   
   public JSONObject MakeUserToJsonLoginResponse(User user) throws JSONException{
	   JSONObject ojson = new JSONObject();
	   if (user.isLoggedin()){
	   ojson.put("status","IN");
	   } else {
		   ojson.put("status","OUT");
	   }
	   
	   return ojson;
   }
   
   public JSONObject MakeSignUpStatusToJson(String signupstatus) throws JSONException{
	   JSONObject ojson = new JSONObject();
	   ojson.put("status", signupstatus);
	   return ojson;
	   
   } 
   
   
}
