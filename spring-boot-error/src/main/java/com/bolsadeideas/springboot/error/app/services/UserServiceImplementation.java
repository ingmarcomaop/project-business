package com.bolsadeideas.springboot.error.app.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.error.app.domain.User;

@Service
public class UserServiceImplementation implements IUserService {

	private List<User> listUser;

	public UserServiceImplementation() {
		this.listUser = new ArrayList<>();
		this.listUser.add(new User(1, "Stephi", "de Ortega"));
		this.listUser.add(new User(2, "Marco", "Ortega"));
		this.listUser.add(new User(3, "Julian", "Ossa"));
		this.listUser.add(new User(4, "Manuel", "Gómez"));
		this.listUser.add(new User(5, "Lalito", "Cat"));
		this.listUser.add(new User(6, "Soila", "Cerda"));
		this.listUser.add(new User(7, "Benito", "Camelas"));
	}

	@Override
	public List<User> toListUser() {

		return this.listUser;
	}

	@Override
	public User getById(Integer id) {

		User result = null;

		for (User u : this.listUser) {
			if (u.getId().equals(id)) {
				result = u;
				break;
			}
		}

		return result;
	}

	@Override
	public Optional<User> getByIdOptional(Integer id) {
		
		User user = getById(id);
		
		return Optional.ofNullable(user);
	}

}
