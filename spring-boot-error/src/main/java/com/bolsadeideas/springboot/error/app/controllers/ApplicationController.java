package com.bolsadeideas.springboot.error.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.bolsadeideas.springboot.error.app.domain.User;
import com.bolsadeideas.springboot.error.app.errors.UserNotFoundException;
import com.bolsadeideas.springboot.error.app.services.IUserService;

@Controller
public class ApplicationController {
	
	@Autowired
	private IUserService userService;
	
	@SuppressWarnings("unused")
	@GetMapping("/index")
	public String index() {
		
		//Integer value = 100/0;
		Integer value = Integer.parseInt("10x");
		
		return "index";
	}
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable Integer id, Model model) {
		
		//User user = userService.getById(id);
		
		/*if(user == null) {
			throw new UserNotFoundException(id.toString());
		}*/
		
		User user = userService.getByIdOptional(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
		
		model.addAttribute("user", user);
		model.addAttribute("title", "User detail: ".concat(user.getName()));
		
		return "show";
	}

}
