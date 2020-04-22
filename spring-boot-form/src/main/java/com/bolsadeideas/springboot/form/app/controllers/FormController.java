package com.bolsadeideas.springboot.form.app.controllers;

import java.sql.Date;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.editors.NameUpperCaseEditor;
import com.bolsadeideas.springboot.form.app.models.domain.Country;
import com.bolsadeideas.springboot.form.app.models.domain.User;
import com.bolsadeideas.springboot.form.app.validations.UserValidator;

@Controller
@SessionAttributes("user")
public class FormController {
	
	@Autowired
	private UserValidator validator;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");	
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, "birthDate", new CustomDateEditor(dateFormat, false));
		
		binder.registerCustomEditor(String.class, "name", new NameUpperCaseEditor());
		binder.registerCustomEditor(String.class, "lastName", new NameUpperCaseEditor());
	}
	
	@ModelAttribute("listCountries")
	public List<Country> listCountries(){
		return Arrays.asList(
				new Country(1, "ES", "España"), 
				new Country(2, "MX","México"),
				new Country(3, "CL","Chile"),
				new Country(4, "COL", "Colombia"),
				new Country(5, "VEN", "Venezuela"),
				new Country(6, "PR", "Perú"));
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
		user.setIdentifier("123.456.789-K");
		model.addAttribute("user", user);
		model.addAttribute("title", "Form users");

		return "form";
	}

	@PostMapping("/form")
	public String process(@Valid User user, BindingResult result, Model model, SessionStatus status) {

		//validator.validate(user,  result);
		model.addAttribute("title", "Result form");
		
		if (result.hasErrors()) {

			/*Map<String, String> errors = new HashMap<>();
			result.getFieldErrors().forEach(err -> {
				errors.put(err.getField(),
						"The field ".concat(err.getField()).concat(" ").concat(err.getDefaultMessage()));
			});

			model.addAttribute("error", errors);*/

			return "form";
		}

		
		model.addAttribute("user", user);
		status.setComplete();

		return "result";
	}

}
