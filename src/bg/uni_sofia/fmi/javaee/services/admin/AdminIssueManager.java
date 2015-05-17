package bg.uni_sofia.fmi.javaee.services.admin;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import bg.uni_sofia.fmi.javaee.dao.IssueDao;
import bg.uni_sofia.fmi.javaee.model.Issue;

@Stateless
@Path("admin/issue")
public class AdminIssueManager {
		
	@EJB
	private IssueDao issueDao;
	
	@POST
	@Path("edit")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response editIssue(Issue issue) { 
		issueDao.editIssue(issue);
		
		return Response.ok().build();
	}
}
