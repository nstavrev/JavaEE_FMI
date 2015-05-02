package bg.uni_sofia.fmi.javaee.dao;

import java.util.List;

import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import bg.uni_sofia.fmi.javaee.model.User;

@Singleton
public class UserDao {

	@PersistenceContext
	private EntityManager em;

	public List<User> getAllUsers() {
		return em.createQuery("SELECT u from User u", User.class)
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
	
	public User findUserByName(String userName) {
        String textQuery = "SELECT u FROM User u WHERE u.userName = :userName";
        TypedQuery<User> query = em.createQuery(textQuery, User.class);
        query.setParameter("userName", userName);
        return queryUser(query);
    }

	private User queryUser(TypedQuery<User> query) {
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
