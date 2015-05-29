package bg.uni_sofia.fmi.javaee.service;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;

import bg.uni_sofia.fmi.javaee.model.User;

@SessionScoped
public class UserContext implements Serializable {


	private static final long serialVersionUID = 3060545566155686560L;
	
	private User currentUser;

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
