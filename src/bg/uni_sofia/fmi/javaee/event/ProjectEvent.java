package bg.uni_sofia.fmi.javaee.event;

import bg.uni_sofia.fmi.javaee.model.Project;

public class ProjectEvent extends AbstractEvent<Project> {

	public ProjectEvent(Project t) {
		super(t);
	}

	
}
