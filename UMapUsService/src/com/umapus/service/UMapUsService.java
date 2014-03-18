package com.umapus.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.umapus.domain.util.UMapUsMapper;
import com.umapus.infrastructure.dao.DAOFactory;

@Controller
@Path("umapusservice")
@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
public class UMapUsService {

	@Autowired
	private DAOFactory daoFactory;

	@Autowired
	private UMapUsMapper mapper;

	@POST
	@Path("/login")
	public String Login(String jsonBody, @Context HttpServletRequest req,
			@Context HttpServletResponse response) {

		boolean loginStatus = false;
		String loginResponse = "{\"status\": \"IN\"}";
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
