package bg.uni_sofia.fmi.javaee.services;


import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

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
	
	@PersistenceContext
	private EntityManager em;
	
	private Gson gson = new Gson();
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewProject(Project newProject) {
		System.out.println("CREATING");
		projectDao.createProject(newProject);
		return Response.ok().build();
	}
	
	@POST
	@Path("newProjectMember/{projectId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addNewProjectMember(User member, @PathParam("projectId") Long projectId) {
		
		projectDao.addMemberInProject(member, projectId);
		
		System.out.println("MEGED IN DAOOOOO1 ");
		return Response.ok().build(); 
	}
	
	@POST
	@Path("removeProjectMember/{projectId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response removeProjectMember(User member, @PathParam("projectId") Long projectId){ 
		projectDao.removeMemberFromProject(member, projectId);
		System.out.println("DELETE MERGE 12 DAO");
		return Response.ok().build();
	}
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Project> getAllProjects() {
		return projectDao.getAllProjects();
	}
	
	@GET
	@Path("id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String findProjectById(@PathParam("id") Long id){
		Project project = projectDao.findProjectById(id);
		if(project != null) {
			project.getMembers();
			return gson.toJson(project);
		}
		return null;
	}
}
