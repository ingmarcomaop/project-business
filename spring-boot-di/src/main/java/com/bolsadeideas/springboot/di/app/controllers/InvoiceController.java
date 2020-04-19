package com.bolsadeideas.springboot.di.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bolsadeideas.springboot.di.app.models.domain.Invoice;

@Controller
@RequestMapping("/invoice")
public class InvoiceController {
	
	@Autowired
	private Invoice invoice;
	
	@GetMapping("/show")
	public String show(Model model) {
		
		model.addAttribute("invoice", invoice);
		model.addAttribute("title", "Invoice Example with Injection Dependency");
		
		return "invoice/show";
		
	}

}
