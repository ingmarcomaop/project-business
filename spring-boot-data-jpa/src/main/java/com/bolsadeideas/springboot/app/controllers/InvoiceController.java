package com.bolsadeideas.springboot.app.controllers;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bolsadeideas.springboot.app.models.entity.Client;
import com.bolsadeideas.springboot.app.models.entity.Invoice;
import com.bolsadeideas.springboot.app.models.entity.ItemInvoice;
import com.bolsadeideas.springboot.app.models.entity.Product;
import com.bolsadeideas.springboot.app.models.service.IClientService;

@Controller
@RequestMapping("/invoice")
@SessionAttributes("invoice")
public class InvoiceController {
	
	@Autowired
	private IClientService clientService;
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@GetMapping("/show/{id}")
	public String show(@PathVariable(value = "id") Long id,
			Model model,
			RedirectAttributes flash) {
		
		Invoice invoice = clientService.findInvoiceById(id);
		
		if(invoice == null) {
			flash.addFlashAttribute("error", "The invoice does not exits!");
			return "redirect:/listclient";
		}
		
		model.addAttribute("invoice", invoice);
		model.addAttribute("title", "Invoice: ".concat(invoice.getDescription()));
		
		return "invoice/show";
	}

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
	
	@PostMapping("/form")
	public String save(@Valid Invoice invoice,
			BindingResult result,
			Model model,
			@RequestParam(name="item_id[]", required = false) Long[] itemId,
			@RequestParam(name="quantity[]", required = false) Integer[] quantity,
			RedirectAttributes flash,
			SessionStatus status) {
		
		if(result.hasErrors()) {
			model.addAttribute("title", "Create invoice");
			return "invoice/form";
		}
		
		if(itemId == null || itemId.length == 0) {
			
			model.addAttribute("title", "Create invoice");
			model.addAttribute("error", "Error: The invoice should have products!");
			
			return "invoice/form";
		}
		
		for(int i = 0; i < itemId.length; i++) {
			Product product = clientService.findProductById(itemId[i]);
			
			ItemInvoice line = new ItemInvoice();
			line.setQuantity(quantity[i]);
			line.setProduct(product);
			invoice.addItemInvoice(line);
			
			log.info("ID: " + itemId[i].toString() + ", quantity: " + quantity[i].toString());
			
		}
		
		clientService.saveInvoice(invoice);
		
		status.setComplete();
		
		flash.addFlashAttribute("success", "Invoice created successfuly!");
		
		return "redirect:/show/" + invoice.getClient().getId();
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		
		Invoice invoice = clientService.findInvoiceById(id);
		
		if(invoice != null) {
			clientService.deleteInvoice(id);
			flash.addFlashAttribute("success", "Invoice deleted successfully");
			return "redirect:/show/" + invoice.getClient().getId();
		}
		
		flash.addFlashAttribute("error", "The invoice does not exits si ti can not be deleted!");
		
		return "redirect:/listclient";
	}
}
