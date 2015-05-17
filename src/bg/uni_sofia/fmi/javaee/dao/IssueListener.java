package bg.uni_sofia.fmi.javaee.dao;

import java.util.List;

import javax.mail.MessagingException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;

import bg.uni_sofia.fmi.javaee.mail.MailSender;
import bg.uni_sofia.fmi.javaee.model.Comment;
import bg.uni_sofia.fmi.javaee.model.Issue;

public class IssueListener {

	private InitialContext context;

	private String mailSenderLookup = "java:global/JavaEE_FMI/MailSender";

	public IssueListener() {
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@PostPersist
	public void onPersist(Issue issue) {
		this.sendMail(issue, "New issue assigned to you", this.getMailTextForPersist(issue));
	}

	@PostUpdate
	public void onUpdate(Issue issue) {
		this.sendMail(issue, "Issue Changes", this.getMailTextForUpdate(issue));
	}

	private void sendMail(Issue issue, String subject, String text) {
		try {
			MailSender sender = (MailSender) context.lookup(this.mailSenderLookup);
			sender.sendMail(issue.getAssignee().getEmail(), subject, text);
		} catch (MessagingException | NamingException e) {
			e.printStackTrace();
		}
	}

	private String getMailTextForPersist(Issue issue) {
		StringBuilder sb = new StringBuilder();
		sb.append("Dear " + issue.getAssignee().getFullName()
				+ " your have new issue with number " + issue.getId()
				+ " assigned to you \n");
		this.appendIssueDetails(issue, sb);
		return sb.toString();
	}

	private String getMailTextForUpdate(Issue issue) {
		StringBuilder sb = new StringBuilder();
		sb.append("Dear " + issue.getAssignee().getFullName()
				+ " your issue with number " + issue.getId()
				+ " was updated \n");
		this.appendIssueDetails(issue, sb);
		return sb.toString();
	}

	private void appendIssueDetails(Issue issue, StringBuilder sb) {
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
