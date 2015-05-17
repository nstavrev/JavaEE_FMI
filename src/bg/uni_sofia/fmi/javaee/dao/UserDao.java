package bg.uni_sofia.fmi.javaee.dao;

import java.security.MessageDigest;
import java.util.List;

import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.uni_sofia.fmi.javaee.model.Role;
import bg.uni_sofia.fmi.javaee.model.User;
import bg.uni_sofia.fmi.javaee.services.UserContext;

/**
 * User Data Access Object - This class provides CRUD operations related to Users
 */
@Singleton
public class UserDao {

	@PersistenceContext
	private EntityManager em;
	
	@Inject
	private UserContext userContext;
	
	public List<User> findAllUsers() {
		String textQuery;
		if(userContext.getCurrentUser().getRole().getName().equals("Administrator")){
			textQuery = "select u from User u";
		} else {
			textQuery = "select u from User u where u.role.name = 'User'";
		}
		System.out.println(textQuery);
		return em.createQuery(textQuery, User.class)
				.getResultList();
	}

	public void addUser(User user) {
		user.setPassword(this.getHashedPassword(user.getPassword()));
		em.persist(user);
	}

	public boolean validateCredentials(String userName, String password) {
		String textQuery = "select u from User u where u.userName=:userName and u.password=:password";
		
		TypedQuery<User> query = em.createQuery(textQuery, User.class)
				.setParameter("userName", userName)
				.setParameter("password", this.getHashedPassword(password));
		return queryUser(query) != null;
	}
	
	public User findUserById(Long id) {
		return em.find(User.class, id);
	}
	
	public Role findRoleById(Long id){
		return em.find(Role.class, id);
	}
	
	public User findUserByName(String userName) {
        String textQuery = "select u from User u where u.userName = :userName";
        TypedQuery<User> query = em.createQuery(textQuery, User.class);
        query.setParameter("userName", userName);
        return queryUser(query);
    }
	
	public List<Role> findAllRoles() {
		String textQuery = "select r from Role r";
		return em.createQuery(textQuery, Role.class).getResultList();
	}
	
	public void removeUserById(Long id) {
		em.remove(this.findUserById(id));
	}

	private User queryUser(TypedQuery<User> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	private String getHashedPassword(String password) {
        try {
            MessageDigest mda = MessageDigest.getInstance("SHA-512");
            password = new String(mda.digest(password.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return password;
    }
}
