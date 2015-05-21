package bg.uni_sofia.fmi.javaee.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: IssueChange
 *
 */
@Entity
@Table(name = "issueshistory")
@SuppressWarnings("unused")
public class IssueHistory implements Serializable {

	private static final long serialVersionUID = 2204800035170999653L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Issue issue;
	
	@OneToOne
	private User editor;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private Date updateDate;
	
	private String title;
	
	private String description;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date dueDate;
	
	@OneToOne
	private User assignee;
	
	@OneToOne
	private IssueStatus status; 
	
	private IssueHistory(){}
	
	public IssueHistory(Issue issue){
		this.issue = issue;
		this.title = issue.getTitle();
		this.description = issue.getDescription();
		this.dueDate = issue.getDueDate();
		this.assignee = issue.getAssignee();
		this.status = issue.getStatus();
	}
	
	public Issue getIssue() {
		return issue;
	}

	public User getEditor() {
		return editor;
	}

	public void setEditor(User editor) {
		this.editor = editor;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Date getUpdateDate() {
		return updateDate;
	}

}
