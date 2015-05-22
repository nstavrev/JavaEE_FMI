package bg.uni_sofia.fmi.javaee.event;

import bg.uni_sofia.fmi.javaee.model.Project;

public class NewProjectEvent extends AbstractEvent<Project> {

	public NewProjectEvent(Project t) {
		super(t);
	}

}
