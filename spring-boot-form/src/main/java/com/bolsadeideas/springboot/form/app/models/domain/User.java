package com.bolsadeideas.springboot.form.app.models.domain;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.bolsadeideas.springboot.form.app.validations.IdentifierRegex;
import com.bolsadeideas.springboot.form.app.validations.Required;

public class User {

	// @Pattern(regexp = "[0-9]{2}[.][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}") // la
	// notación \\d es cualquier digito de 0 a 9 (validating regular expression)
	@IdentifierRegex
	private String identifier;

	@NotEmpty(message = "Please, type your name.")
	private String name;

	// @NotEmpty
	@Required
	private String lastName;

	@NotBlank
	@Size(min = 3, max = 8)
	private String username;

	@NotEmpty //Valida strings
	private String password;

	@Required
	@Email // (message = "The email format is incorrect.")
	private String email;

	@Min(5)
	@Max(5000)
	@NotNull
	private Integer account;

	@NotNull //Valida objetos
	@Past
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@NotNull
	private Country country;
	
	@NotEmpty
	private List<Role> roles;
	
	private Boolean enable;
	
	@NotEmpty
	private String gender;
	
	private String secretValue;

	public String getSecretValue() {
		return secretValue;
	}

	public void setSecretValue(String secretValue) {
		this.secretValue = secretValue;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	public Integer getAccount() {
		return account;
	}

	public void setAccount(Integer account) {
		this.account = account;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}

}
