package bg.uni_sofia.fmi.javaee.services;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import bg.uni_sofia.fmi.javaee.dao.IssueDao;
import bg.uni_sofia.fmi.javaee.model.Issue;
import bg.uni_sofia.fmi.javaee.utils.DataTableObject;

@Stateless
@Path("issue")
public class IssueManager {
	
	private Gson gson = new Gson();
	
	@EJB
	private IssueDao issueDao;
	
	@GET
	@Path("/all/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIssuesByProjectId(@PathParam("projectId") Long id){
		List<Issue> projectIssues = issueDao.findIssuesByProjectId(id);
		DataTableObject<Issue> dataTableObject = new DataTableObject<Issue>(projectIssues);
		return gson.toJson(dataTableObject); 
	}
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewIssue(Issue newIssue) {
		System.out.println(newIssue.getAssignee().getUserName());
		return Response.ok().build();
	}
	
}
