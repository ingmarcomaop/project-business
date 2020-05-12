package com.bolsadeideas.springboot.app.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.app.models.entity.Client;

public interface IClientService {

	public List<Client> findAll();
	
	public Page<Client> findAll(Pageable pageable);

	public void saveClient(Client client);

	public Client findOne(Long id);

	public void delete(Long id);

}