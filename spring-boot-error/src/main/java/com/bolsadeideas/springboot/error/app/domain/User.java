package com.bolsadeideas.springboot.error.app.domain;

public class User {

	private Integer id;
	private String name;
	private String lastName;
	

	public User() {

	}

	public User(Integer id, String name, String lastName) {

		this.name = name;
		this.lastName = lastName;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
