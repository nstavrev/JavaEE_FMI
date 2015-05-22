package bg.uni_sofia.fmi.javaee.event;

import javax.enterprise.event.Observes;

import bg.uni_sofia.fmi.javaee.model.Issue;

public class NewIssueNotifier extends Notifier {
	
	public void sendMailToReporter(@Observes NewIssueEvent event) {
		Issue issue = event.get();
		String text = "Dear " + issue.getReporter().getFullName() + " your issue with number "
				+ "" + issue.getId() + " was saved successfully";
		String subject = "New Issue";
		notify(issue.getReporter().getEmail(), subject, text);;
	}
	
	public void sendMailToAssignee(@Observes NewIssueEvent event) {
		System.out.println("assigneee");
		Issue issue = event.get();
		String text = "Dear " + issue.getAssignee().getFullName()
				+ " you have assigned to issue " + issue.getTitle();
		notify(issue.getAssignee().getEmail(), "Assigned issue", text);
	}
	
}
