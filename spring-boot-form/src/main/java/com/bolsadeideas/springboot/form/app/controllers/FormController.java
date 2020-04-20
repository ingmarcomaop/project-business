package com.bolsadeideas.springboot.form.app.controllers;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bolsadeideas.springboot.form.app.models.domain.User;

@Controller
@SessionAttributes("user")
public class FormController {

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
