package com.bolsadeideas.springboot.di.app;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.bolsadeideas.springboot.di.app.models.domain.ItemInvoice;
import com.bolsadeideas.springboot.di.app.models.domain.Product;
import com.bolsadeideas.springboot.di.app.models.service.IMyService;
import com.bolsadeideas.springboot.di.app.models.service.MyService;
import com.bolsadeideas.springboot.di.app.models.service.MyServiceComplex;

@Configuration
public class AppConfiguration {

	@Bean("mySimpleService")
	public IMyService registerMyService() {
		return new MyService();
	}

	@Bean("myComplexService")
	@Primary
	public IMyService registerMyComplexService() {
		return new MyServiceComplex();
	}
	
	@Bean("itemsInvoice")
	public List<ItemInvoice> itemResgitration(){
		
		Product product1 = new Product("Sony Camera", 100);
		Product product2 = new Product("Race Bycicle", 350);
		
		ItemInvoice line1 = new ItemInvoice(product1, 2);
		ItemInvoice line2 = new ItemInvoice(product2, 4);
		
		return Arrays.asList(line1, line2);
	}

}
