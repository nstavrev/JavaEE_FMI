package bg.uni_sofia.fmi.javaee.mail;

import java.util.List;

import javax.ejb.Singleton;

import bg.uni_sofia.fmi.javaee.model.Comment;
import bg.uni_sofia.fmi.javaee.model.Issue;

@Singleton
public class IssueMailSender extends MailSender<Issue> {

	@Override
	protected String getMailTextForPersist(Issue issue) {
		StringBuilder sb = new StringBuilder();
		sb.append("Dear " + issue.getAssignee().getFullName()
				+ " your have new issue with number " + issue.getId()
				+ " assigned to you \n");
		this.appendDetails(issue, sb);
		return sb.toString();
	}

	@Override
	protected String getMailTextForUpdate(Issue issue) {
		StringBuilder sb = new StringBuilder();
		sb.append("Dear " + issue.getAssignee().getFullName()
				+ " your issue with number " + issue.getId()
				+ " was updated \n");
		this.appendDetails(issue, sb);
		return sb.toString();
	}

	@Override
	protected void appendDetails(Issue issue, StringBuilder sb) {
		sb.append("Title : " + issue.getTitle() + "\n");
		sb.append("Description : " + issue.getDescription() + "\n");
		sb.append("Due Date : " + issue.getDueDate() + "\n");
		sb.append("Status : " + issue.getStatus().getName() + "\n");
		sb.append("\n");
		List<Comment> comments = issue.getComments();
		if (comments != null && !comments.isEmpty()) {
			sb.append("Comments \n");
			for (Comment comment : comments) {
				sb.append("\n");
				sb.append("Content : \n");
				sb.append(comment.getContent() + "\n");
				sb.append("Date : " + comment.getCreationDate() + "\n");
				sb.append("Creator : " + comment.getCreator().getFullName()
						+ "\n");
			}
		}
		
	}

}
