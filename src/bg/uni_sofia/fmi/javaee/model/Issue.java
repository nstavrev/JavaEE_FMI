package bg.uni_sofia.fmi.javaee.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import bg.uni_sofia.fmi.javaee.dao.IssueListener;

/**
 * Entity implementation class for Entity: Issue
 *
 */
@Entity
@Table(name = "issues")
@EntityListeners(IssueListener.class)
public class Issue implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8224864254519441971L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	private String title;
	
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date creationDate = new Date();
	
	@ManyToOne
	@JoinColumn(name = "status_id", nullable = false)
	private IssueStatus status;
	
	@ManyToOne
	@JoinColumn(name = "project_id", nullable = false)
	private Project project;
	
	@ManyToOne
	@JoinColumn(name = "assignee_id", nullable = false)
	private User assignee;
	
	@ManyToOne
	@JoinColumn(name = "reporter_id", nullable = false)
	private User reporter;
	
	@OneToMany(mappedBy = "issue", cascade = CascadeType.ALL)  
	private List<Comment> comments; 
	
	@OneToMany(mappedBy = "issue", cascade = CascadeType.PERSIST)
	private List<IssueHistory> history;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public IssueStatus getStatus() {
		return status;
	}

	public void setStatus(IssueStatus status) {
		this.status = status;
	}

	public User getAssignee() {
		return assignee;
	}

	public void setAssignee(User assignee) {
		this.assignee = assignee;
	}

	public User getReporter() {
		return reporter;
	}

	public void setReporter(User reporter) {
		this.reporter = reporter;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<IssueHistory> getHistory() {
		return history;
	}

	public void setHistory(List<IssueHistory> history) {
		this.history = history;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((assignee == null) ? 0 : assignee.hashCode());
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		System.out.println("eqq");
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Issue other = (Issue) obj;
		if (assignee == null) {
			if (other.assignee != null)
				return false;
		} else if (!assignee.equals(other.assignee))
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	
}
