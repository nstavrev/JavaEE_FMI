package bg.uni_sofia.fmi.javaee.event;

import bg.uni_sofia.fmi.javaee.model.Issue;

public class NewIssueEvent extends AbstractEvent<Issue> {

	public NewIssueEvent(Issue t) {
		super(t);
	}
	
}
