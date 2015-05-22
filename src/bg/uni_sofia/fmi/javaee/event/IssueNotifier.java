package bg.uni_sofia.fmi.javaee.event;

import javax.enterprise.event.Observes;

import bg.uni_sofia.fmi.javaee.model.Issue;

public class IssueNotifier extends Notifier {
	
	public void sendMailToReporter(@Observes IssueEvent event) {
		Issue issue = event.get();
		String text = "Dear " + issue.getReporter().getFullName() + " your issue with number "
				+ "" + issue.getId() + " was edited";
		String subject = "Changed Issue";
		notify(issue.getReporter().getEmail(), subject, text);;
	}
	
	public void sendMailToAssignee(@Observes IssueEvent event) {
		Issue issue = event.get();
		String text = "Dear " + issue.getAssignee().getFullName()
				+ " your issue " + issue.getTitle() + " was edited";
		notify(issue.getAssignee().getEmail(), "Changed Assigned issue", text);
	}

}
