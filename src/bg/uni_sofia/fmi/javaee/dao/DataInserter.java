package bg.uni_sofia.fmi.javaee.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import bg.uni_sofia.fmi.javaee.model.User;

@Singleton
@Startup
public class DataInserter {
	
	private final static Logger LOGGER = Logger.getLogger(DataInserter.class.getName()); 
	
	@EJB 
	private UserDao userDao;
	
	@PostConstruct
	public void insert() {
		if (userDao.findUserByName("admin") == null) {
			User user = new User();
			user.setUserName("admin");
			user.setFullName("Admin");
			user.setPassword("123456");
			user.setEmail("n.stavrev28@gmail.com");
			user.setRole(userDao.findRoleById(1l));
			userDao.addUser(user);
			LOGGER.log(Level.INFO,
					"Administrator with username " + user.getUserName()
							+ " and password " + user.getPassword()
							+ " has been created successfully");
		} else { 
			LOGGER.log(Level.INFO, "User with username Admin is found");
		}
	}
}
