package bg.uni_sofia.fmi.javaee.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.uni_sofia.fmi.javaee.model.Comment;
import bg.uni_sofia.fmi.javaee.model.Issue;
import bg.uni_sofia.fmi.javaee.model.IssueStatus;

@Singleton
public class IssueDao {

	@PersistenceContext
	private EntityManager em;
	
	public Issue findIssueById(Long id) {
		return em.find(Issue.class, id);
	}
	
	public List<Comment> findIssueComments(Long issueId) {
		String textQuery = "select c from Comment c where c.issue.id =:issueId";
		TypedQuery<Comment> query = em.createQuery(textQuery, Comment.class).setParameter("issueId", issueId);
		List<Comment> comments = query.getResultList();
		
		return comments;
	}
	
	public List<Issue> findAllIssues() {
		String textQuery = "select i from Issue i";
		List<Issue> issues = em.createQuery(textQuery, Issue.class).getResultList();
		return issues;
	}
	
	public List<Issue> findIssuesByProjectId(Long projectId) {
		String textQuery = "select i from Issue i where i.project.id =:projectId";
		TypedQuery<Issue> query = em.createQuery(textQuery,Issue.class);
		query.setParameter("projectId", projectId);
		List<Issue> issues = query.getResultList();
		
		return issues;
	}
	
	public List<IssueStatus> findAllStatuses() {
		String textQuery = "select s from IssueStatus s";
		return em.createQuery(textQuery, IssueStatus.class).getResultList();
	}
	
	public IssueStatus findStatusById(Long id) {
		return em.find(IssueStatus.class, id);
	}
	
	public void createNewIssue(Issue issue) {
		em.persist(issue);
	}
	
	public void editIssue(Issue issue){
		em.merge(issue);
	}
	
}