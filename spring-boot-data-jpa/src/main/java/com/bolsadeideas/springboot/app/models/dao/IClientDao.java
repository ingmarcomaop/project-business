package com.bolsadeideas.springboot.app.models.dao;

import java.util.List;

import com.bolsadeideas.springboot.app.models.entity.Client;

public interface IClientDao {
	
	public List<Client> findAll();
	
	public void saveClient(Client client);

}
