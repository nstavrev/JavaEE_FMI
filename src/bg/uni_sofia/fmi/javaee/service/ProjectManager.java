package bg.uni_sofia.fmi.javaee.service;


import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.uni_sofia.fmi.javaee.dao.ProjectDao;
import bg.uni_sofia.fmi.javaee.dao.UserDao;
import bg.uni_sofia.fmi.javaee.model.Project;
import bg.uni_sofia.fmi.javaee.model.User;

@Stateless
@Path("project")
public class ProjectManager {
	
	@EJB
	private ProjectDao projectDao;
	
	@EJB
	private UserDao userDao;
	
	@Inject
	private UserContext context;
	
	@PersistenceContext
	private EntityManager em;
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({ "Administrator" })
	public Response createNewProject(Project newProject) {
		newProject.setCreator(context.getCurrentUser());
		projectDao.createProject(newProject);  
		return Response.ok().build();
	}
	
	@POST
	@Path("newProjectMember/{projectId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({"Administrator"}) 
	public Response addNewProjectMember(User member, @PathParam("projectId") Long projectId) {
		System.out.println("add member"); 
		projectDao.addMemberInProject(member, projectDao.findProjectById(projectId));
		
		return Response.ok().build(); 
	}
	
	@POST
	@Path("removeProjectMember/{projectId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@RolesAllowed({"Administrator"})
	public Response removeProjectMember(User member, @PathParam("projectId") Long projectId){ 
		projectDao.removeMemberFromProject(member, projectId);
		return Response.ok().build();
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public List<Project> getAllProjects() { 
		return projectDao.findAllProjects();
	}
	
	@GET
	@Path("id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Project findProjectById(@PathParam("id") Long id){
		Project project = projectDao.findProjectById(id);
		if(project != null) { 
			project.getMembers();
			return project;
		}
		return null;
	}
	
	@DELETE 
	@Path("remove")
	@RolesAllowed({"Administrator"})
	public Response removeProject(@QueryParam("id") Long id) {
		projectDao.removeProject(id);
		return Response.ok().build(); 
	}
	
	@GET
	@Path("count")
	@Produces(MediaType.APPLICATION_JSON)
	@PermitAll
	public Long countAllProjects() {
		return projectDao.countAllProjects();
	}
}
