package com.bolsadeideas.springboot.form.app.validations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;

import org.springframework.util.StringUtils;

public class RequiredValidator implements ConstraintValidator<Required, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		//if(value == null || !StringUtils.hasText(value)) Otra manera de construir la misma condición de abajo.
			
		
		
		if(value == null || value.isEmpty() || value.isBlank()) {
			return false;
		}else { return true; }
		
	}

}
