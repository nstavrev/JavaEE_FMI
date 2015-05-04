package bg.uni_sofia.fmi.javaee.services;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.uni_sofia.fmi.javaee.dao.UserDao;
import bg.uni_sofia.fmi.javaee.model.User;

@Stateless
@Path("auth")
public class AuthService {
	
	@EJB
	private UserDao userDao;
	
	@Inject
	private UserContext context;
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user) {
		boolean isValid = userDao.validateCredentials(user.getUserName(), user.getPassword());
		
		if(!isValid) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}
		
		context.setCurrentUser(userDao.findUserByName(user.getUserName()));
		
		return Response.ok().build();
	}
	
	@GET
	@Path("logout")
	public void logout(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect(request.getContextPath() + "/login.html");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
