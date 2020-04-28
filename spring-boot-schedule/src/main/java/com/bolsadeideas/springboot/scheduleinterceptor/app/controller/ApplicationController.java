package com.bolsadeideas.springboot.scheduleinterceptor.app.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
	
	@Value("${config.schedule.open}")
	private Integer open;
	
	@Value("${config.schedule.close}")
	private Integer close;
	
	@GetMapping({"/", "index"})
	private String index(Model model) {
		model.addAttribute("title", "Welcome to client schedule attention");
		return "index";
	}
	
	@GetMapping("/close")
	public String close(Model model) {
		
		StringBuilder message = new StringBuilder("Closed, please visti us to");
		
		message.append(open);
		message.append(" hours form ");
		message.append(close);
		message.append(" hours. Thanks.");
		
		model.addAttribute("title", "Out of attention hour");
		model.addAttribute("message", message);
		
		return "close";
	}
	

}
