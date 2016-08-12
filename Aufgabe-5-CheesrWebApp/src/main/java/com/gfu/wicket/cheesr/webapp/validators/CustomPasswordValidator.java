package com.gfu.wicket.cheesr.webapp.validators;


import java.util.regex.Pattern;

import org.apache.wicket.validation.IValidatable;
import org.apache.wicket.validation.IValidator;
import org.apache.wicket.validation.ValidationError;

public class CustomPasswordValidator implements IValidator<String> {

	private static final long serialVersionUID = 6914956587601132034L;

	private final String PASSWORD_PATTERN = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})";
	
	/*(		   # Start of group
			  (?=.*\d)			#   must contain one digit from 0-9
			  (?=.*[a-z])		#   must contain one lowercase characters
			  (?=.*[A-Z])		#   must contain one uppercase characters
			       		.		#   match anything with previous condition checking
		              {6,20}	#        length at least 6 characters and maximum of 20	
			) # End of group
			?= – means apply the assertion condition, meaningless by itself, always work with other combination

			6 to 20 characters string with at least one digit, one upper case letter, one lower case letter
	*/
	
	private final Pattern pattern;

	public CustomPasswordValidator() {
		pattern = Pattern.compile(PASSWORD_PATTERN);
	}

	@Override
	public void validate(IValidatable<String> validatable) {

		final String password = validatable.getValue();
		// validate password
		if (pattern.matcher(password).matches() == false) {

			error(validatable, "weakPassword");
		}
	}

	private void error(IValidatable<String> validatable, String errorKey) {

		System.err.println("<CustomPasswordValidator>: Password validation error! ");
		ValidationError error = new ValidationError();
		error.addKey(errorKey);
		validatable.error(error);
	}
}
