package bg.uni_sofia.fmi.javaee.services.admin;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import bg.uni_sofia.fmi.javaee.dao.ProjectDao;

@Stateless
@Path("admin/project")
public class AdminProjectManager {
	
	@EJB
	private ProjectDao projectDao;
	
	@DELETE 
	@Path("remove")
	public Response removeProject(@QueryParam("id") Long id) {
		projectDao.removeProject(id);
		return Response.ok().build(); 
	}
	
}
