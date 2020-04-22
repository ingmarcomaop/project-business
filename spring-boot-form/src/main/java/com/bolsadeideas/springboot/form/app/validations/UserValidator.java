package com.bolsadeideas.springboot.form.app.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.bolsadeideas.springboot.form.app.models.domain.User;

@Component
public class UserValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {

		return User.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		//User user = (User) target;
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NotEmpty.user.name");
		
		/*
		 * if(user.getIdentifier().matches("[0-9]{2}[.][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")
		 * == false) { errors.rejectValue("identifier", "pattern.user.identifier"); }
		 */

	}

}
