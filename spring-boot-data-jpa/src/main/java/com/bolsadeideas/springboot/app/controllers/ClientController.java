package com.bolsadeideas.springboot.app.controllers;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.service.IClientService;
import com.bolsadeideas.springboot.app.util.paginator.PageRender;

@Controller
@SessionAttributes("client")
public class ClientController {

	@Autowired
	private IClientService clientService;
	
	@GetMapping(value = "/show/{id}")
	public String show(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Client client = clientService.findOne(id);
		
		if(client == null) {
			flash.addFlashAttribute("error", "The client does not exist");
			return "redirect:/listclient";
		}
		
		model.put("client", client);
		model.put("title", "Client Detail: " + client.getName());
		
		return "show";
	}
	
	@RequestMapping(value="/listclient", method=RequestMethod.GET)
	public String toListClient(@RequestParam(name="page", defaultValue = "0") int page, Model model) {
		
		Pageable pageRequest = PageRequest.of(page, 4);
		
		Page<Client> clients = clientService.findAll(pageRequest);
		
		PageRender<Client> pageRender = new PageRender<>("/listclient", clients);
		
		model.addAttribute("title", "Clients List");
		model.addAttribute("clients", clients);
		model.addAttribute("page", pageRender);
		
		return "listclient";
		
	}
	
	@RequestMapping(value="/form")
	public String create(Map<String, Object> model) {
		
		Client client = new Client();
		model.put("client", client);
		model.put("title", "Client Form");
		
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	public String edit(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Client client = null;
		
		if(id > 0) {
			client = clientService.findOne(id);
			if(client == null) {
				flash.addFlashAttribute("error", "The client id does not exist");
				return "redirect:/listclient";
			}
			
		} else {
			flash.addFlashAttribute("error", "The client id could not be zero");
			return "redirect:/listclient";
		}
		
		model.put("client", client);
		model.put("title", "Edit Client");
		
		return "form";
	}
	
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String save(@Valid Client client, BindingResult result, Model model, @RequestParam("file") MultipartFile foto, RedirectAttributes flash, SessionStatus status) {
		
		if(result.hasErrors()) {
			
			model.addAttribute("title", "Client Form");
			
			return "form";
		}
		
		if(!foto.isEmpty()) {
		
			//String rootPath = "C://tmp/uploads"; //windows
			String rootPath = "/home/marcoortega/temp-spring-boot/uploads";
			
			try {
				byte[] bytes = foto.getBytes();
				Path pathComplete = Paths.get(rootPath + "//" + foto.getOriginalFilename());
				Files.write(pathComplete, bytes);
				flash.addFlashAttribute("info", "The file '" + foto.getOriginalFilename() + "' has been uploaded successfuly");
				
				client.setFoto(foto.getOriginalFilename());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
			
			
			
		
		String messageFlash = (client.getId() != null)? "Client edited successfully" : "Client created successfully";
		
		clientService.saveClient(client);
		status.setComplete();
		flash.addFlashAttribute("success", messageFlash);
		
		return "redirect:listclient";
	}
	
	@RequestMapping(value="/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		if(id > 0) {
			clientService.delete(id);
			flash.addFlashAttribute("success", "Client deleted successfully");
		}
		
		return "redirect:/listclient";
	}
	
	
}
