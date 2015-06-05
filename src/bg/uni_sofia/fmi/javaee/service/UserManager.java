package bg.uni_sofia.fmi.javaee.service;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.uni_sofia.fmi.javaee.dao.UserDao;
import bg.uni_sofia.fmi.javaee.model.Role;
import bg.uni_sofia.fmi.javaee.model.User;
import bg.uni_sofia.fmi.javaee.util.DataTableObject;

@Stateless
@Path("user")
public class UserManager {
	
	@EJB
	private UserDao userDao;
	
	@Inject
	private UserContext userContext;
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<User> users(@Context HttpServletRequest request) {
		return userDao.findAllUsers();
	} 
	
	@GET
	@Path("role")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Role getCurrentUserRole(){ 
		User user = userDao.findUserById(userContext.getCurrentUser().getId());
		return user.getRoles().get(0);
	}
	
	@GET
	@Path("users")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "Administrator" })
	public DataTableObject<User> getAllUsers() {
		List<User> users = userDao.findAllUsers();
		for (User user : users) {
			user.getRoles();
		}
		DataTableObject<User> dataTableObject = new DataTableObject<User>(users);
		return dataTableObject;
	}
	
	@GET
	@Path("roles")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "Administrator" })
	public List<Role> getAllRoles() {
		return userDao.findAllRoles();
	}
	
	@POST
	@Path("register")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "Administrator" })
	public Response register(User user) {
		userDao.addUser(user);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("remove") 
	@RolesAllowed({ "Administrator" })
	public Response removeUser(@QueryParam("id") Long id) {
		userDao.removeUserById(id);
		return Response.ok().build();
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	@RolesAllowed({"Administrator"})
	public Long countAllUsers() {
		return userDao.countAllUsers();
	}

}
