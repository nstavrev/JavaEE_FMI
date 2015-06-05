package bg.uni_sofia.fmi.javaee.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.uni_sofia.fmi.javaee.event.NewProjectEvent;
import bg.uni_sofia.fmi.javaee.event.ProjectEvent;
import bg.uni_sofia.fmi.javaee.model.Project;
import bg.uni_sofia.fmi.javaee.model.User;

/**
 * Project Data Access Object - This class provides CRUD operations related to Projects
 */
@Singleton
public class ProjectDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@EJB
	private UserDao userDao;
	
	@Inject
	private Event<NewProjectEvent> newProjectEvent;
	
	@Inject 
	private Event<ProjectEvent> projectEvent;
	
	public void createProject(Project project){
		em.persist(project);
		newProjectEvent.fire(new NewProjectEvent(project));
	}
	
	public void editProject(Project project){
		em.merge(project);
		projectEvent.fire(new ProjectEvent(project));
	}
	
	public void addMemberInProject(User member, Project project) {
		User userFromDB = userDao.findUserById(member.getId());
		List<User> members = project.getMembers();
		if(!members.contains(userFromDB)){
			members.add(userFromDB);
			em.merge(project); 
			userFromDB.getProjects().add(project);
			em.merge(userFromDB);
			projectEvent.fire(new ProjectEvent(project));
		}
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

	public List<Project> findAllProjects() {
		String textQuery = "select p from Project p";
		TypedQuery<Project> query = em.createQuery(textQuery, Project.class);
		List<Project> projects = query.getResultList();
		return projects;
	}
	
	public Long countAllProjects() {
		String textQuery = "select count(p.id) from Project p";
		TypedQuery<Long> query = em.createQuery(textQuery, Long.class);
		return query.getSingleResult();
	}
	
	public Project findProjectById(Long id) {
		return em.find(Project.class, id);
	}
	
	public void removeProject(Long id) {
		em.remove(this.findProjectById(id));
	}
	
}
