package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.app.models.dao.IClientDao;
import com.bolsadeideas.springboot.app.models.entity.Client;

@Service
public class ClientServiceImplementation implements IClientService{
	
	@Autowired
	private IClientDao clientDao;

	@Override
	@Transactional(readOnly = true)
	public List<Client> findAll() {
		
		return (List<Client>) clientDao.findAll();
	}

	@Override
	@Transactional
	public void saveClient(Client client) {
		clientDao.save(client);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Client findOne(Long id) {
		
		return clientDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		clientDao.deleteById(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Client> findAll(Pageable pageable) {
		
		return clientDao.findAll(pageable);
	}

}
