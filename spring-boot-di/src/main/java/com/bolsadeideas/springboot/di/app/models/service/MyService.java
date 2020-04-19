package com.bolsadeideas.springboot.di.app.models.service;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Service
//@Component("mySimpleService")
//@Primary
public class MyService implements IMyService{

	@Override
	public String operation() {
		return "executing something important simple...";
	}
	
}
