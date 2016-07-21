package com.gfu.wicket.cheesr.webapp.validators;

import org.apache.wicket.validation.INullAcceptingValidator;
import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class SimpleCustomValidator implements IValidator<String>, INullAcceptingValidator<String>{

	private String fieldName;

	public SimpleCustomValidator() {
		// TODO Auto-generated constructor stub
	}
	
	public SimpleCustomValidator(String fieldName) {
		this.fieldName = fieldName;
	}
	
	@Override
	public void validate(IValidatable<String> validatable) {
		
		String value = validatable.getValue();
		
		if(value == null)
		{
			// Message from key "SimpleCustomValidator.notEmpty"
			System.err.println("<SimpleCustomValidator>: Validating... ");
			error(validatable, "notEmpty");
		}
		
		if(value != null && value.length() < 2)
		{
			// Message from key "SimpleCustomValidator.tooShort"
			System.err.println("<SimpleCustomValidator>: Validating... ");
			error(validatable, "tooShort");
		}
	}	
	
	private void error(IValidatable<String> validatable, String errorKey) {
		
		System.err.println("<SimpleCustomValidator>: Validation error! ");
		ValidationError error = new ValidationError();
		
		if(fieldName != null)
			error.addKey(fieldName + "." + errorKey); // Bezug zum Feldnamen herstellen
		else {
			error.addKey(errorKey);
		}
		
		validatable.error(error);
	}

}
