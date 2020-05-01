package com.bolsadeideas.springboot.app.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bolsadeideas.springboot.app.models.dao.IClientDao;
import com.bolsadeideas.springboot.app.models.entity.Client;

@Controller
public class ClientController {

	@Autowired
	private IClientDao clientDao;
	
	@RequestMapping(value="/listclient", method=RequestMethod.GET)
	public String toListClient(Model model) {
		
		model.addAttribute("title", "Clients list");
		model.addAttribute("clients", clientDao.findAll());
		
		return "listclient";
		
	}
	
	@RequestMapping(value="/form")
	public String create(Map<String, Object> model) {
		
		Client client = new Client();
		model.put("client", client);
		model.put("title", "Client Form");
		
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String save(Client client) {
		
		clientDao.saveClient(client);
		
		return "redirect:listclient";
	}
	
	
}
