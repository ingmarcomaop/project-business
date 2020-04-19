package com.bolsadeideas.springboot.web.app.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.web.app.models.User;

@Controller
@RequestMapping("/app")
public class IndexController {
	
	@Value("${text.indexcontroller.index.title}")
	private String textIndex;
	
	@Value("${text.indexcontroller.index.profile}")
	private String textProfile;
	
	@Value("${text.indexcontroller.index.tolist}")
	private String textToList;
	
	@RequestMapping(value = {"/index", "/", "", "/home"}, method = RequestMethod.GET) 
	public String index(Model model) {
		
		model.addAttribute("titulo", textIndex);
		return "index";
	}
	
	@RequestMapping("/profile")
	public String profileModel(Model model) {
		
		User user = new User();
		user.setName("Marco");
		user.setLastName("Ortega");
		user.setEmail("ing.marcomaop@gmail.com");
		
		model.addAttribute("user", user);
		model.addAttribute("titulo", textProfile.concat(user.getName()));
		
		return "profile";		
	}
	
	@RequestMapping("/tolist")
	public String tolist(Model model) {
		
		model.addAttribute("titulo", textToList);
		
		return "tolist";
	}
	
	@ModelAttribute("users")
	public List<User> fillUser(){
		List<User> users = Arrays.asList(
				new User("Marco", "Ortega", "ing.marcomaop@gmail.com"),
				new User("Andrés", "Guzmán", "correo@gmail.com"),
				new User("Luciana", "Ortega", "luciana@gmail.com"),
				new User("Miguel Angel", "Ortega", "miguelito@gmail.com")
				);
		
		return users;
		
	}
	
}
