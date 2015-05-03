package bg.uni_sofia.fmi.javaee.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
			for (User member : members) {
				this.addMemberInProject(member, project.getId());
			}
		}
	}
	
	public void addMemberInProject(User member, Long projectId) {
		User userFromDB = userDao.findUserByName(member.getUserName());
		
		Project projectFromDB = this.findProjectById(projectId);
		projectFromDB.getMembers().add(userFromDB);
		userFromDB.getProjects().add(projectFromDB);
		em.merge(projectFromDB);
		em.merge(userFromDB);
	}
	
	public void removeMemberFromProject(User member, Long projectId) {
		Project project = this.findProjectById(projectId);
		User userFromDB = userDao.findUserByName(member.getUserName());
		userFromDB.getProjects().remove(project);
		em.merge(userFromDB);
	}
	
	public Project updateProject(Project project) {
		return em.merge(project);
	}

	public List<Project> getAllProjects() {
		return em.createQuery("SELECT p from Project p", Project.class).getResultList();
	}
	
	public Project findProjectById(Long id) {
		return em.find(Project.class, id);
	}
	
}
