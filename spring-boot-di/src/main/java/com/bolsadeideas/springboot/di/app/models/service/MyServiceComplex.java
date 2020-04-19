package com.bolsadeideas.springboot.di.app.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Service
//@Component("myComplexService")
public class MyServiceComplex implements IMyService{

	@Override
	public String operation() {
		return "executing something important complex...";
	}
	
}
