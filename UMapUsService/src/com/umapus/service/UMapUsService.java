package com.umapus.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.json.JSONException;

import com.umapus.domain.util.UMapUsMapper;
import com.umapus.infrastructure.dao.DAOFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
@Path("umapusservice")
public class UMapUsService {

	
	@Autowired
	private DAOFactory daoFactory;
	
	@Autowired
	private UMapUsMapper mapper;
	

	@POST
	@Path("/login")
	public String Login(String jsonBody, @Context HttpServletRequest req) {

		boolean loginStatus = false;
		String loginResponse = "OUT";
		try {
			loginStatus = daoFactory.getLdapDao().LoginUser(
					mapper.MapJsonToLoginRequest(jsonBody));
			if (loginStatus) {
				loginResponse = "{\"status\": \"IN\"}";
				HttpSession session = req.getSession(true);
				Object username = session.getAttribute("username");
				if (username != null) {
					System.out.println(username.toString());
				} else {

					session.setAttribute("username", mapper
							.MapJsonToLoginRequest(jsonBody).getuserName());
				}
			}
		} catch (NamingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return loginResponse;
	}

	@POST
	@Path("/signup")
	public String SignUp(String jsonBody) {

		String gid = "Success";
		try {
			gid = daoFactory.getLdapDao().CreateLDAPUser(
					mapper.MapJsonToSignupRequest(jsonBody));
		} catch (NamingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return gid;
	}

}
