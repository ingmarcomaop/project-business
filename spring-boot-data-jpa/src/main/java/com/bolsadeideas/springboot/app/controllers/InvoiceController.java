package com.bolsadeideas.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.Invoice;
import com.bolsadeideas.springboot.app.models.entity.Product;
import com.bolsadeideas.springboot.app.models.service.IClientService;

@Controller
@RequestMapping("/invoice")
@SessionAttributes("invoice")
public class InvoiceController {
	
	@Autowired
	private IClientService clientService;

	@GetMapping("/form/{clientId}")
	public String create(@PathVariable(value = "clientId") Long clientId, 
			Map<String, Object> model, 
			RedirectAttributes flash) {
		
		Client client = clientService.findOne(clientId);
		
		if(client == null) {
			flash.addAttribute("error", "The client does not exit");
			return "redirect:/listclient"; 
		}
		
		Invoice invoice = new Invoice();
		invoice.setClient(client);
		
		model.put("invoice", invoice);
		model.put("title", "Create Invoice");
		
		return "invoice/form";
	}
	
	@GetMapping(value = "/load-products/{term}", produces = {"application/json"})
	public @ResponseBody List<Product> loadProducts(@PathVariable String term){
		return clientService.findByName(term);	
	}
}
