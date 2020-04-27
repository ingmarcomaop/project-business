package com.bolsadeideas.springboot.form.app.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.CountryPropertyEditor;
import com.bolsadeideas.springboot.form.app.editors.NameUpperCaseEditor;
import com.bolsadeideas.springboot.form.app.editors.RolesEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Country;
import com.bolsadeideas.springboot.form.app.models.domain.Role;
import com.bolsadeideas.springboot.form.app.models.domain.User;
import com.bolsadeideas.springboot.form.app.services.CountryService;
import com.bolsadeideas.springboot.form.app.services.RoleService;
import com.bolsadeideas.springboot.form.app.validations.UserValidator;

@Controller
@SessionAttributes("user")
public class FormController {
	
	@Autowired
	private UserValidator validator;
	
	@Autowired
	private CountryService countryService;
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private CountryPropertyEditor countryEditor;
	
	@Autowired
	private RolesEditor roleEditor;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(dateFormat, false));
		
		binder.registerCustomEditor(String.class, "name", new NameUpperCaseEditor());
		binder.registerCustomEditor(String.class, "lastName", new NameUpperCaseEditor());
		
		binder.registerCustomEditor(Country.class, "country", countryEditor);
		binder.registerCustomEditor(Role.class, "roles", roleEditor);
	}
	
	@ModelAttribute("listGender")
	public List<String> listGender(){
		return Arrays.asList("Male", "Famale");
	}
	
	@ModelAttribute("listRoles")
	public List<Role> listRoles(){
		return this.roleService.toListRole();
	}
	
	@ModelAttribute("listCountries")
	public List<Country> listCountries(){
		return countryService.toListCountry();
	}
	
	@ModelAttribute("listRolesString")
	public List<String> listRolesString(){
		List<String> roles = new ArrayList<>();
		roles.add("ROLE_ADMIN");
		roles.add("ROLE_USER");
		roles.add("ROLE_MODERATOR");
		
		return roles;
	}
	
	@ModelAttribute("listRolesMap")
	public Map<String, String> listRolesMap(){
		
		Map<String, String> roles = new HashMap<String, String>();
		roles.put("ROLE_ADMIN", "Admin");
		roles.put("ROLE_USER", "User");
		roles.put("ROLE_MODERATOR", "Moderator");

		
		return roles;
	}
	
	@ModelAttribute("countries")
	public List<String> countires(){
		return Arrays.asList("España", "México", "Chile", "Colombia", "Venezuela", "Perú");
	}
	
	@ModelAttribute("countiresMap")
	public Map<String, String> countiresMap(){
		
		Map<String, String> countries = new HashMap<String, String>();
		countries.put("ES", "España");
		countries.put("MX", "México");
		countries.put("CL", "Chile");
		countries.put("COL", "Colombia");
		countries.put("VEN", "Venezuela");
		countries.put("PR", "Perú");
		
		return countries;
	}

	@GetMapping("/form")
	public String form(Model model) {

		User user = new User();
		
		user.setName("Jhon");
		user.setLastName("Doe");
		user.setIdentifier("12.456.789-K");
		user.setEnable(true);
		user.setSecretValue("Some secret value ***");
		user.setCountry(new Country(4, "COL", "Colombia"));
		user.setRoles(Arrays.asList(new Role(2, "User", "ROL_USER")));
		
		model.addAttribute("user", user);
		model.addAttribute("title", "Form users");
		

		return "form";
	}

	@PostMapping("/form")
	public String process(@Valid User user, BindingResult result, Model model) {

		if (result.hasErrors()) {

			model.addAttribute("title", "Result form");
			
			return "form";
		}

		return "redirect:/show";
	}
	
	@GetMapping("/show")
	public String show(@SessionAttribute(name= "user", required = false) User user, Model model, SessionStatus status) {
		
		if(user == null) {
			return "redirect:/form";
		}
		
		model.addAttribute("title", "Result form");
		
		status.setComplete();
		
		return "result";
	}
	

}
