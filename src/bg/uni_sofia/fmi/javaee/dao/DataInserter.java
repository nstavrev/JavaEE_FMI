package bg.uni_sofia.fmi.javaee.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import bg.uni_sofia.fmi.javaee.model.Role;
import bg.uni_sofia.fmi.javaee.model.User;

@Singleton
@Startup
public class DataInserter {
	
	private final static Logger LOGGER = Logger.getLogger(DataInserter.class.getName()); 
	
	@EJB 
	private UserDao userDao;
	
	@PersistenceContext
	private EntityManager em;
	
	@PostConstruct
	public void insert() {
		List<Role> roles = userDao.findAllRoles();
		if(roles == null || roles.isEmpty()){
			Role admin = new Role();
			admin.setName("Administrator");
			Role user = new Role();
			user.setName("User");
			em.persist(admin);
			em.persist(user);
			
		}
		if (userDao.findUserByName("admin") == null) {
			User user = new User();
			user.setUserName("admin");
			user.setFullName("Admin");
			user.setPassword("123456");
			user.setEmail("n.stavrev28@gmail.com");
			TypedQuery<Role> queryForRole = em.createQuery("select role from Role role where role.name =:role", Role.class).setParameter("role", "Administrator");
			user.setRole(queryForRole.getSingleResult());
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
