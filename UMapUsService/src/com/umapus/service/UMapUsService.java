package com.umapus.service;

import javax.naming.NamingException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.json.JSONException;

import com.umapus.domain.util.UMapUsMapper;
import com.umapus.infrastructure.dao.DAOFactory;



@Path("umapusservice")
public class UMapUsService {


   static DAOFactory daoFactory = new DAOFactory();
   static UMapUsMapper mapper = new UMapUsMapper();
   
	
	@POST
	@Path("/login")
	public String Login(String jsonBody) {
		boolean loginStatus = false;
		String loginResponse = "OUT";
		try{
			loginStatus = daoFactory.getLdapDao().LoginUser(mapper.MapJsonToLoginRequest(jsonBody));
			if (loginStatus) {
				loginResponse = "IN";
			}
		} catch (NamingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return loginResponse;
	}
	
  
	@POST
	@Path("/signup")
	 public  String SignUp(String jsonBody){
	    
		String gid = "Success";
	    try {
		     gid = daoFactory.getLdapDao().
					       CreateLDAPUser(mapper.MapJsonToSignupRequest(jsonBody));
		} catch (NamingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return gid;
	}
	
}


