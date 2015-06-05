package bg.uni_sofia.fmi.javaee.service;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.ServletException;
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
public class AuthManager {
	
	@EJB
	private UserDao userDao;
	
	@Inject
	private UserContext context;
	
	@POST
	@Path("login")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response login(User user, @Context HttpServletRequest request) {
		try {
			System.out.println("login");
			request.login(user.getUserName(), userDao.getHashedPassword(user.getPassword()));
			context.setCurrentUser(userDao.findUserByName(user.getUserName()));
			return Response.ok().build();
		} catch (ServletException e) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		} 
	}
	
	@POST 
	@Path("test")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response test(User user, @Context HttpServletRequest request){
		try {
			System.out.println("login");
			request.login(user.getUserName(), userDao.getHashedPassword(user.getPassword()));
			context.setCurrentUser(userDao.findUserByName(user.getUserName()));
			return Response.ok().build();
		} catch (ServletException e) {
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		} 
	}
	
	@GET
	@Path("logout")
	public void logout(@Context HttpServletRequest request, @Context HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect(request.getContextPath() + "/login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}
