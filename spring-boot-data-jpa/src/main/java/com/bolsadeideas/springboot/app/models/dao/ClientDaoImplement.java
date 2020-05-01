package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.entity.Client;

@Repository
public class ClientDaoImplement implements IClientDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<Client> findAll() {
		
		return em.createQuery("from Client").getResultList();
	}

	@Override
	@Transactional
	public void saveClient(Client client) {
		
		em.persist(client);
		
	}

}
