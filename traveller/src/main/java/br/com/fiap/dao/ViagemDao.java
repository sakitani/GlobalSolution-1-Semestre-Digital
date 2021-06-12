package br.com.fiap.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.model.User;
import br.com.fiap.model.Viagem;
import br.com.fiap.util.EntityManagerFacade;

public class ViagemDao {
	
	private EntityManager manager = EntityManagerFacade.get();

	public void save(Viagem viagem) {
		
		
		manager.getTransaction().begin();
		manager.persist(viagem);
		manager.getTransaction().commit();
		
		manager.close();
	}
	
	public Viagem findById(Long id) {
		
		return manager.find(Viagem.class, id);
	}
	
	public List<Viagem> getAll() {
		
		return manager.createQuery("SELECT u from Viagem u", Viagem.class).getResultList();
		
		}
	

	public void update(Viagem viagem) {
		manager.getTransaction().begin();
		manager.merge(viagem);
		manager.flush();
		manager.getTransaction().commit();
		
	}
	
	public void delete(Long id) {
		
		manager.getTransaction().begin();
		Viagem viagem = manager.find(Viagem.class, id);
		manager.remove(viagem);
		manager.flush();
		manager.getTransaction().commit();
	}

	

}
