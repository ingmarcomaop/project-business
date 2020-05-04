package com.bolsadeideas.springboot.app.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.bolsadeideas.springboot.app.models.entity.Client;

public interface IClientDao extends PagingAndSortingRepository<Client, Long>{
	
	
}
