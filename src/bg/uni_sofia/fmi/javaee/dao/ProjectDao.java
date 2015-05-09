package bg.uni_sofia.fmi.javaee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.uni_sofia.fmi.javaee.model.Project;
import bg.uni_sofia.fmi.javaee.model.User;

@Singleton
public class ProjectDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private UserDao userDao;
	
	public void createProject(Project project){
		em.persist(project);
		if(project.getMembers() != null){
			List<User> members = new ArrayList<User>(project.getMembers());
			//ConcurrentModification Exception
			for (User member : members) {
				this.addMemberInProject(member, project.getId());
			}
		}
	}
	
	public void addMemberInProject(User member, Long projectId) {
		User userFromDB = userDao.findUserById(member.getId());
		
		Project projectFromDB = this.findProjectById(projectId);
		projectFromDB.getMembers().add(userFromDB);
		em.merge(projectFromDB); 
		userFromDB.getProjects().add(projectFromDB);
		em.merge(userFromDB); 
	}
	
	public void removeMemberFromProject(User member, Long projectId) {
		Project project = this.findProjectById(projectId);
		User userFromDB = userDao.findUserById(member.getId());
		userFromDB.getProjects().remove(project);
		em.merge(userFromDB);
	}
	
	public Project updateProject(Project project) {
		return em.merge(project);
	}

	public List<Project> getAllProjects() {
		String textQuery = "select p from Project p";
		TypedQuery<Project> query = em.createQuery(textQuery, Project.class);
		List<Project> projects = query.getResultList();
		return projects;
	}
	
	public Project findProjectById(Long id) {
		return em.find(Project.class, id);
	}
	
	public void removeProject(Long id) {
		em.remove(this.findProjectById(id));
	}
	
}
