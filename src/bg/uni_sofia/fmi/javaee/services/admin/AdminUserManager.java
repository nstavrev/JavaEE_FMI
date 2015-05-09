package bg.uni_sofia.fmi.javaee.services.admin;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.uni_sofia.fmi.javaee.dao.UserDao;
import bg.uni_sofia.fmi.javaee.model.Role;
import bg.uni_sofia.fmi.javaee.model.User;
import bg.uni_sofia.fmi.javaee.utils.DataTableObject;

@Stateless
@Path("admin/user")
public class AdminUserManager {
	
	@EJB
	private UserDao userDao;
	
	@GET
	@Path("users")
	@Produces(MediaType.APPLICATION_JSON)
	public DataTableObject<User> getAllUsers() {
		List<User> users = userDao.findAllUsers();
		DataTableObject<User> dataTableObject = new DataTableObject<User>(users);
		return dataTableObject;
	}
	
	@GET
	@Path("roles")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Role> getAllRoles() {
		return userDao.findAllRoles();
	}
	
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response register(User user) {
		userDao.addUser(user);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("remove") 
	public Response removeUser(@QueryParam("id") Long id) {
		userDao.removeUserById(id);
		return Response.ok().build();
	}

}
