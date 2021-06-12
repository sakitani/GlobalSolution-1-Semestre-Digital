package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.User;
import br.com.fiap.util.EntityManagerFacade;

public class UserDao {
	
	private EntityManager manager = EntityManagerFacade.get();

	public void save(User user) {
		
		
		manager.getTransaction().begin();
		manager.persist(user);
		manager.getTransaction().commit();
		
		manager.close();
	}
	
	public User findById(Long id) {
		
		return manager.find(User.class, id);
	}
	
	public List<User> getAll() {
		
		return manager.createQuery("SELECT u from User u", User.class).getResultList();
		
		}
	

	public void update(User user) {
		manager.getTransaction().begin();
		manager.merge(user);
		manager.flush();
		manager.getTransaction().commit();
		
	}
	
	public void delete(Long id) {
		
		manager.getTransaction().begin();
		User user = manager.find(User.class, id);
		manager.remove(user);
		manager.flush();
		manager.getTransaction().commit();
	}

	public boolean exist(User user) {
		TypedQuery<User> query = manager.createQuery("SELECT p FROM User p WHERE "
				+ "email=:email AND "
				+ "password=:password", User.class);
		query.setParameter("email", user.getEmail());
		query.setParameter("password", user.getPassword());
		User result;
		try {
			result = query.getSingleResult();
			if (result != null) return true;
			return false;
		} catch (Exception e) {
			return false;
		
		}
		
	}

}
