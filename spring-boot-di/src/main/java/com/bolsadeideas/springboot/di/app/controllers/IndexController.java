package com.bolsadeideas.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.bolsadeideas.springboot.di.app.models.service.IMyService;


@Controller
public class IndexController {
	
	//@Autowired
	//@Qualifier("myComplexService")
	//private IMyService service;
	
	@Autowired
	private IMyService service;
	
	@GetMapping({"", "/", "/index"})
	public String index(Model model) {
		
		model.addAttribute("object", service.operation());
		return "index";
	}


	
	

}
