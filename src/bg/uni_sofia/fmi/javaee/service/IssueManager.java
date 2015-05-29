package bg.uni_sofia.fmi.javaee.service;

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

import bg.uni_sofia.fmi.javaee.dao.IssueDao;
import bg.uni_sofia.fmi.javaee.model.Comment;
import bg.uni_sofia.fmi.javaee.model.Issue;
import bg.uni_sofia.fmi.javaee.model.IssueStatus;
import bg.uni_sofia.fmi.javaee.util.DataTableObject;
import bg.uni_sofia.fmi.javaee.util.DonutChartData;

@Stateless
@Path("issue")
public class IssueManager {
	
	@EJB
	private IssueDao issueDao;
	
	@Inject
	private UserContext context;
	
	@GET
	@Path("all")
	@Produces(MediaType.APPLICATION_JSON)
	public DataTableObject<Issue> getAllIssues() {
		List<Issue> allIssues = issueDao.findAllIssues();
		DataTableObject<Issue> dataTableObject = new DataTableObject<Issue>(allIssues);
		return dataTableObject;
	}
	
	@GET
	@Path("/all/{projectId}")
	@Produces(MediaType.APPLICATION_JSON)
	public DataTableObject<Issue> getIssuesByProjectId(@PathParam("projectId") Long id){
		List<Issue> projectIssues = issueDao.findIssuesByProjectId(id);
	
		DataTableObject<Issue> dataTableObject = new DataTableObject<Issue>(projectIssues);
		return dataTableObject;
	}
	
	@GET
	@Path("statuses")
	@Produces(MediaType.APPLICATION_JSON)
	public List<IssueStatus> getAllIssueStatuses() { 
		List<IssueStatus> statuses = issueDao.findAllStatuses();
		return statuses;
	}
	
	@GET
	@Path("id/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Issue getIssueById(@PathParam("id") Long id) {
		return issueDao.findIssueById(id);
	}
	
	@POST
	@Path("new")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createNewIssue(Issue newIssue) {
		long initialIssues = issueDao.findUserIssuesNumberByStatus(newIssue
				.getAssignee().getId(), IssueStatus.INITIAL);
		long ongoingIssues = issueDao.findUserIssuesNumberByStatus(newIssue
				.getAssignee().getId(), IssueStatus.ONGOING);
		
		issueDao.createNewIssue(newIssue); 
		
		if(initialIssues > 2 || ongoingIssues > 2) { 
			return Response.ok().entity("Warning.This user has more than 2 ongoing or inital issues").build();
		}
		
		return Response.ok().build();
	}
	
	@POST
	@Path("changeStatus")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response changeIssueStatus(Issue issue){
		Issue issueFromDB = issueDao.findIssueById(issue.getId());
		issueFromDB.setStatus(issue.getStatus()); 
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
	public List<Comment> getCommentsByIssueId(@PathParam("issueId") Long issueId){
		return issueDao.findIssueComments(issueId);
	}
	
	@GET
	@Path("donutData")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DonutChartData> getDonutData() {
		return issueDao.getDonutDataForUserIssues();
	}
	
}
