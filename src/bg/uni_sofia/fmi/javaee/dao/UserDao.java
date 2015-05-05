package bg.uni_sofia.fmi.javaee.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.uni_sofia.fmi.javaee.model.Role;
import bg.uni_sofia.fmi.javaee.model.User;

@Singleton
public class UserDao {

	@PersistenceContext
	private EntityManager em;

	public List<User> findAllUsers() {
		String textQuery = "SELECT u from User u";
		return em.createQuery(textQuery, User.class)
				.getResultList();
	}

	public void addUser(User user) {
		em.persist(user);
	}

	public boolean validateCredentials(String userName, String password) {
		String textQuery = "SELECT u FROM User u WHERE u.userName=:userName AND u.password=:password";
		
		TypedQuery<User> query = em.createQuery(textQuery, User.class)
				.setParameter("userName", userName)
				.setParameter("password", password);
		return queryUser(query) != null;
	}
	
	public User findUserById(Long id) {
		return em.find(User.class, id);
	}
	
	public User findUserByName(String userName) {
        String textQuery = "SELECT u FROM User u WHERE u.userName = :userName";
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
}
