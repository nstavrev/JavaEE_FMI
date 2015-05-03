package bg.uni_sofia.fmi.javaee.services;

import java.net.HttpURLConnection;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.uni_sofia.fmi.javaee.dao.UserDao;
import bg.uni_sofia.fmi.javaee.model.User;

@Stateless
@Path("user")
public class UserManager {
	
	@EJB
	private UserDao userDao;
	
	@Inject
	private UserContext context;
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> users() {
		return userDao.getAllUsers();
	}
	
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
	
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(User user) {
		userDao.addUser(user);
		context.setCurrentUser(user);
		return Response.ok().build();
	}

}
