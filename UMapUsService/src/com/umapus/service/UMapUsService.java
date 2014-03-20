package com.umapus.service;

import java.util.HashMap;
import java.util.Map;

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

import com.umapus.domain.entity.LoginRequest;
import com.umapus.domain.entity.SignUpResponse;
import com.umapus.domain.entity.UMapUsConstants;
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
	public Response Login(String jsonBody, @Context HttpServletRequest req)
			throws JSONException {

		// boolean loginStatus = false;
		NewCookie cookie = null;

		String loginResponse = "{\"status\": \"OUT\"}";
		try {
			// String loginUser =
			// mapper.MapJsonToLoginRequest(jsonBody).getuserName();

			user = LoginUser(mapper.MapJsonToLoginRequest(jsonBody), req);

			if (user.isLoggedin()) {
				HashMap<String, String> attrbs = new HashMap<String, String>();
				attrbs.put("username", user.getEmailId());
				attrbs.put("sn", user.getSurname());
				attrbs.put("graphid", user.getGraphId());

				HttpSession session = createSession(req, attrbs);
				// session.setAttribute("username", loginUser);
				cookie = createCookie(session);

			}
		} catch (NamingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return Response.status(201)
				.entity(mapper.MakeUserToJsonLoginResponse(user).toString())
				.cookie(cookie).build();
	}

	@POST
	@Path("/signup")
	@Produces(MediaType.APPLICATION_JSON)
	public Response SignUp(String jsonBody, @Context HttpServletRequest req) {

		String signupstatus = "Success";
		Response rep = null;
		try {
			signupstatus = daoFactory.getLdapDao().CreateLDAPUser(
					mapper.MapJsonToSignupRequest(jsonBody));
			if (signupstatus.equals(SignUpResponse.SUCCESS.getStatus())) {
				rep = Login(jsonBody, req);
			} else {
				return Response
						.status(201)
						.entity(mapper.MakeSignUpStatusToJson(signupstatus)
								.toString()).build();

			}
		} catch (NamingException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return rep;
	}

	private HttpSession createSession(HttpServletRequest req,
			HashMap<String, String> attributesmap) {

		HttpSession session = req.getSession(true);

		session.setMaxInactiveInterval(UMapUsConstants.SessionMaxInActiveTimeinSec);
//		for (Map.Entry<String, String> entry : attributesmap.entrySet()) {
//			session.setAttribute(entry.getKey(), entry.getValue());
//		}
	   addSessionToRepository(session.getId(),
				attributesmap);
		
		return session;
	}

	private void addSessionToRepository(String sessionId,
			HashMap<String, String> hv) {

		 daoFactory.getSessionRepoDao().AddToRedis(
				"Session:"+sessionId, hv);
		
	}

	private NewCookie createCookie(HttpSession session) {

		return new NewCookie("id", session.getId());
	}

	private User LoginUser(LoginRequest loginRequest, HttpServletRequest req)
			throws NamingException {

		user = daoFactory.getLdapDao().LoginUser(loginRequest);
		return user;

	}

}
