package bg.uni_sofia.fmi.javaee.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import bg.uni_sofia.fmi.javaee.model.Project;

@Singleton
public class ProjectDao {
	
	@PersistenceContext
	private EntityManager em;
	
	public void createProject(Project project){
		em.persist(project);
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
