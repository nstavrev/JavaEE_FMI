package bg.uni_sofia.fmi.javaee.services;

import java.net.HttpURLConnection;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
import bg.uni_sofia.fmi.javaee.model.Comment;
import bg.uni_sofia.fmi.javaee.model.Issue;
import bg.uni_sofia.fmi.javaee.model.IssueStatus;
import bg.uni_sofia.fmi.javaee.utils.DataTableObject;

@Stateless
@Path("issue")
public class IssueManager {
	
	private Gson gson = new Gson();
	
	@EJB
	private IssueDao issueDao;
	
	@Inject
	private UserContext context;
	
	@GET
	@Path("/all/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIssuesByProjectId(@PathParam("projectId") Long id){
		List<Issue> projectIssues = issueDao.findIssuesByProjectId(id);
	
		DataTableObject<Issue> dataTableObject = new DataTableObject<Issue>(projectIssues);
		return gson.toJson(dataTableObject); 
	}
	
	@GET
	@Path("statuses")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllIssueStatuses() { 
		List<IssueStatus> statuses = issueDao.findAllStatuses();
		return gson.toJson(statuses);
	}
	
	@GET
	@Path("id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getIssueById(@PathParam("id") Long id) {
		return gson.toJson(issueDao.findIssueById(id));
	}
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewIssue(Issue newIssue) {
		System.out.println("Insert Reporter 1");
		if(context.getCurrentUser() == null){
			return Response.status(HttpURLConnection.HTTP_UNAUTHORIZED).build();
		}
		
		newIssue.setReporter(context.getCurrentUser());
		newIssue.setCreationDate(new Date());
		System.out.println("hopa " + newIssue.getStatus().getId());
		issueDao.createNewIssue(newIssue); 
		return Response.ok().build();
	}
	
	@POST
	@Path("edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editIssue(Issue issue) { 
		System.out.println("opa 123");
		issueDao.editIssue(issue);
		return Response.ok().build();
	}
	
	@POST
	@Path("addComment/{issueId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(Comment comment, @PathParam("issueId") Long issueId) {
		Issue issue = issueDao.findIssueById(issueId);
		comment.setIssue(issue);
		comment.setCreationDate(new Date());
		comment.setCreator(context.getCurrentUser());
		issue.getComments().add(comment);
		
		return Response.ok().build();
	}
	
	@GET
	@Path("comments/{issueId}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCommentsByIssueId(@PathParam("issueId") Long issueId){
		return gson.toJson(issueDao.findIssueComments(issueId));
	}
	
}
