package bg.uni_sofia.fmi.javaee.event;

import bg.uni_sofia.fmi.javaee.model.Issue;

public class IssueEvent extends AbstractEvent<Issue>  {

	public IssueEvent(Issue t) {
		super(t);
	}
	
}
