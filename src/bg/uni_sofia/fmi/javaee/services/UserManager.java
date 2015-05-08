package bg.uni_sofia.fmi.javaee.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.gson.Gson;

import bg.uni_sofia.fmi.javaee.dao.UserDao;
import bg.uni_sofia.fmi.javaee.model.User;

@Stateless
@Path("user")
public class UserManager {
	
	@EJB
	private UserDao userDao;
	
	@Inject
	private UserContext userContext;
	
	private Gson gson = new Gson();
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> users() {
		return userDao.findAllUsers();
	}
	
	@GET
	@Path("role")
	@Produces
	public String getCurrentUserRole(){
		return gson.toJson(userContext.getCurrentUser().getRole());
	}

}
