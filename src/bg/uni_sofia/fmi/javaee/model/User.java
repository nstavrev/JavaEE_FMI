package bg.uni_sofia.fmi.javaee.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: User
 *
 */
@Entity
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "username"))
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7451849211295986990L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false)
	private String userName;
	
	@Column(nullable = false)
	private String password;  
	
	private String email;
	
	private String fullName;
	
	@ManyToMany
	  @JoinTable(
	      name="users_roles",
	      joinColumns={@JoinColumn(name="username", referencedColumnName="userName", nullable = false)},
	      inverseJoinColumns={@JoinColumn(name="rolename", referencedColumnName="name", nullable = false)})
	private List<Role> roles; 
	
	@OneToMany(mappedBy = "assignee", cascade=CascadeType.REMOVE)
	private List<Issue> issues;
	
	@OneToMany(mappedBy = "reporter", cascade = CascadeType.REMOVE)
	private List<Issue> reportedIssues; 
	
	@OneToMany(mappedBy = "creator", cascade = CascadeType.REMOVE) 
	private List<Comment> comments;
	
	@ManyToMany(cascade = CascadeType.ALL) 
	private List<Project> projects;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<Issue> getIssues() {
		return issues;
	}

	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
	public List<Issue> getReportedIssues() {
		return reportedIssues;
	}

	public void setReportedIssues(List<Issue> reportedIssues) {
		this.reportedIssues = reportedIssues;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
