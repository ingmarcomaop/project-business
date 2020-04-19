package com.bolsadeideas.springboot.di.app.models.domain;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Component
@RequestScope
//@SessionScope
//@ApplicationScope
public class Invoice implements Serializable{

	private static final long serialVersionUID = -6924589479331070016L;

	@Value("${invoice.description}")
	private String description;
	
	@Autowired
	private Client client;
	
	@Autowired
	@Qualifier("itemsInvoiceOffice")
	private List<ItemInvoice> items;
	
	@PostConstruct
	public void init() {
		client.setName(client.getName().concat(" ").concat("Jose"));
		description = description.concat(" of client ").concat(client.getName());
	}
	
	@PreDestroy
	public void destroy() {
		System.out.println("Invoice destroyed: ".concat(description));
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<ItemInvoice> getItems() {
		return items;
	}

	public void setItems(List<ItemInvoice> items) {
		this.items = items;
	}

}
