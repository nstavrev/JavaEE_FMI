package bg.uni_sofia.fmi.javaee.event;

import bg.uni_sofia.fmi.javaee.model.User;

public class NewUserEvent extends AbstractEvent<User> {

	public NewUserEvent(User t) {
		super(t);
	}
	
}
