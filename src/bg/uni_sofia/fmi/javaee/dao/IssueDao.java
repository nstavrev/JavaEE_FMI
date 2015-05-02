package bg.uni_sofia.fmi.javaee.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.uni_sofia.fmi.javaee.model.Issue;

@Singleton
public class IssueDao {

	@PersistenceContext
	private EntityManager em;

	public List<Issue> findIssuesByProjectId(Long projectId) {
		String textQuery = "SELECT i from Issue i where i.project.id =:projectId";
		TypedQuery<Issue> query = em.createQuery(textQuery,Issue.class);
		query.setParameter("projectId", projectId);
		return query.getResultList();
	}
}
