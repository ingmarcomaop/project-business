package com.bolsadeideas.springboot.error.app.services;

import java.util.List;
import java.util.Optional;

import com.bolsadeideas.springboot.error.app.domain.User;

public interface IUserService {
	
	public List<User> toListUser();
	public User getById(Integer id);
	public Optional<User> getByIdOptional(Integer id);

}
