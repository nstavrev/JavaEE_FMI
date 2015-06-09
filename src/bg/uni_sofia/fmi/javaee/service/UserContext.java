package bg.uni_sofia.fmi.javaee.service;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;

import bg.uni_sofia.fmi.javaee.dao.UserDao;
import bg.uni_sofia.fmi.javaee.model.User;

@SessionScoped
public class UserContext implements Serializable {

	@EJB
	private UserDao userDao;
	
	private static final long serialVersionUID = 3060545566155686560L;
	
	private User currentUser;

	public User getCurrentUser() {
		if(currentUser == null) {
			System.out.println("Nqma nikoi tuk");
		}
		return userDao.findUserByName(currentUser.getUserName());
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
