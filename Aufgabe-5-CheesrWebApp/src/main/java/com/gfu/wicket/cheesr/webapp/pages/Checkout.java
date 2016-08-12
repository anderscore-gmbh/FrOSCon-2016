package com.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.form.validation.EqualPasswordInputValidator;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.util.parse.metapattern.MetaPattern;
import org.apache.wicket.validation.validator.EmailAddressValidator;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.gfu.wicket.backend.BOServices;
import com.gfu.wicket.backend.bo.UserInfo;
import com.gfu.wicket.backend.bo.Cart;
import com.gfu.wicket.cheesr.webapp.validators.CustomPasswordValidator;
import com.gfu.wicket.cheesr.webapp.validators.SimpleCustomValidator;

public class Checkout extends CheesrPage {

	private static final long serialVersionUID = 1L;

	public Checkout() {
		
		Form<UserInfo> form = new Form<UserInfo>("form");
		form.add(new FeedbackPanel("feedback")); // feedbackPanel for validationMessages
		
		UserInfo address = getCart().getBillingAddress();
		
		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(address, "name"));
		TextField<String> street = new TextField<String>("street", new PropertyModel<String>(address, "street"));
		TextField<String> zipcode = new TextField<String>("zipcode", new PropertyModel<String>(address, "zipcode"));
		TextField<String> city = new TextField<String>("city", new PropertyModel<String>(address, "city"));
		
		TextField<String> email = new TextField<String>("email", new PropertyModel<String>(address, "email"));
		PasswordTextField password = new PasswordTextField("password", new PropertyModel<String>(address, "password"));
		PasswordTextField repeatPassword = new PasswordTextField("repeat-password");
		repeatPassword.setDefaultModel(new Model<>()); // dummy model for repeat password field
		
		// uncomment following setRequired(...) lines if you want to use INullAcceptingValidator
		name.setRequired(true);
		street.setRequired(true);
		zipcode.setRequired(true);
		city.setRequired(true);
		
		email.setRequired(false);
		password.setRequired(false);
		repeatPassword.setRequired(false);
		
		name.add(new SimpleCustomValidator("name")); 
		street.add(new SimpleCustomValidator("street")); 
		
		// zipcode field only 5 digits (Fehlermeldung in properties anpassen)
//		PatternValidator zipcodeValidator = new PatternValidator("\\d{1,5}");
		// alternatively use MetaPattern:
		PatternValidator zipcodeValidator = new PatternValidator(
				new MetaPattern(
						MetaPattern.DIGITS,
						new MetaPattern("{1,5}")
				)
			); 
				
		zipcode.add(zipcodeValidator);
		
		city.add(new StringValidator(2, 25)); // use StringValidator for min/max length validation
		email.add(EmailAddressValidator.getInstance());
		
		password.add(new CustomPasswordValidator()); 
		repeatPassword.add(new CustomPasswordValidator());
		// alternatively use PatternValidator
//		repeatPassword.add(new PatternValidator("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})"));
		
		form.add(new EqualPasswordInputValidator(password, repeatPassword));
		
		form.add(name);
		form.add(street);
		form.add(zipcode);
		form.add(city);
		form.add(email);
		form.add(password);
		form.add(repeatPassword);
		
		form.add(new Link("cancel") {
			private static final long serialVersionUID = 1L;

			@Override
			public void onClick() {
				setResponsePage(Index.class);
			}
		});
		form.add(new Button("order") {
			@Override
			public void onSubmit() {
				BOServices.get().proccessOrder(getCart());
				Cart cart = getCart();
				cart.getCheeses().clear();
				setResponsePage(Index.class);
			};
		});
		
		add(form);
	}
}
