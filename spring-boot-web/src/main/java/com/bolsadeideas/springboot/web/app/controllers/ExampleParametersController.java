package com.bolsadeideas.springboot.web.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/parameters")
public class ExampleParametersController {

	@GetMapping("")
	public String index(Model model) {

		model.addAttribute("title", "Sending an HTTP GET Request - URL");

		return "parameters/index";
	}

	@GetMapping("/string")
	public String parameters(@RequestParam(name = "text", defaultValue = "There is not a text written\n"
			+ "			for a message") String text, Model model) {

		model.addAttribute("title", "Getting an HTTP GET Request - URL");
		model.addAttribute("result", "The getted text is: " + text);
		return "parameters/view";
	}

	@GetMapping("/mix-parameters")
	public String parameters(@RequestParam String greeting, @RequestParam Integer number, Model model) {

		model.addAttribute("title", "Greeting by Parameters");
		model.addAttribute("result", "The greeting sended: " + greeting + " and the number sended is: " + number);
		return "parameters/view";
	}

	@GetMapping("/mix-parameters-request")
	public String parameters(HttpServletRequest request, Model model) {

		String greeting = request.getParameter("greeting");
		Integer number = null;
		
		model.addAttribute("title", "Greeting by Parameters");
		
		try {
			number = Integer.parseInt(request.getParameter("number"));
		} catch (NumberFormatException e) {
			number = 0;
		}
		
		model.addAttribute("result", "The greeting sended: " + greeting + " and the number sended is: " + number);
		return "parameters/view";
	}

}
