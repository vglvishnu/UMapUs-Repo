package com.umapus.service;

import javax.naming.NamingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.umapus.domain.entity.User;
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
	
	@Autowired
	private User user;

	@POST
	@Path("/login")
	@Produces(MediaType.APPLICATION_JSON)
	public Response Login(String jsonBody, @Context HttpServletRequest req) throws JSONException {

//		boolean loginStatus = false;
		NewCookie cookie = null;
		

//		String loginResponse = "{\"status\": \"OUT\"}";
		try {
			String loginUser = mapper.MapJsonToLoginRequest(jsonBody)
					.getuserName();
			
			user = daoFactory.getLdapDao().LoginUser(
					mapper.MapJsonToLoginRequest(jsonBody));
			
			if (user.isLoggedin()) {
//				loginResponse = "{\"status\": \"IN\"}";
				HttpSession session = createSession(req);
				Object username = session.getAttribute("username");
				
				if (username != null && username.toString().equals(loginUser)) {
					//session.setAttribute("username", loginUser);
					System.out.println(username.toString());
				} else {

					session.setAttribute("username", loginUser);
				}
			   // cookie = new Cookie("id", session.getId());
			     cookie = new NewCookie("id", session.getId());
			    
			}
		} catch (NamingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return Response.status(201).entity(mapper.MakeJsonLoginResponseFromUser(user).toString()).cookie(cookie).build();
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

	private HttpSession createSession(HttpServletRequest req) {

		HttpSession session = req.getSession(true);
		session.setMaxInactiveInterval(30 * 60);
		

		return session;
	}

}
