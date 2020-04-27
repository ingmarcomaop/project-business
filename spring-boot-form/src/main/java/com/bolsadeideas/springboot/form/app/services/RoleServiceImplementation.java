package com.bolsadeideas.springboot.form.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.bolsadeideas.springboot.form.app.models.domain.Role;

@Service
public class RoleServiceImplementation implements RoleService {

	private List<Role> roles;
	
	public RoleServiceImplementation() {
		
		this.roles = new ArrayList<>();
		this.roles.add(new Role(1, "Administrator", "ROL_ADMIN"));
		this.roles.add(new Role(2, "User", "ROL_USER"));
		this.roles.add(new Role(3, "Moderator", "ROL_MODERATOR"));
	}

	@Override
	public List<Role> toListRole() {
		
		return roles;
	}

	@Override
	public Role getRoleById(Integer id) {
		
		Role result = null;
		
		for(Role role: roles) {
			if(id == role.getId()) {
				result = role;
				break;
			}
		}
		return result;
	}

}
