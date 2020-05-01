package com.bolsadeideas.springboot.error.app.errors;

public class UserNotFoundException extends RuntimeException {

	
	
	public UserNotFoundException(String id) {
		super("User with the ID: ".concat(id).concat(" does not exist on system"));
		
	}

	private static final long serialVersionUID = 1L;

}
