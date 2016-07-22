package com.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.PatternValidator;
import org.apache.wicket.validation.validator.StringValidator;

import com.gfu.wicket.backend.BOServices;
import com.gfu.wicket.backend.bo.Address;
import com.gfu.wicket.backend.bo.Cart;
import com.gfu.wicket.cheesr.webapp.validators.CustomPasswordValidator;
import com.gfu.wicket.cheesr.webapp.validators.SimpleCustomValidator;

public class Checkout extends CheesrPage {

	private static final long serialVersionUID = 1L;

	public Checkout() {
		
		Form<Address> form = new Form<Address>("form");
		form.add(new FeedbackPanel("feedback")); // feedbackPanel for validationMessages
		
		Address address = getCart().getBillingAddress();
		
		TextField<String> name = new TextField<String>("name", new PropertyModel<String>(address, "name"));
		TextField<String> street = new TextField<String>("street", new PropertyModel<String>(address, "street"));
		TextField<String> zipcode = new TextField<String>("zipcode", new PropertyModel<String>(address, "zipcode"));
		TextField<String> city = new TextField<String>("city", new PropertyModel<String>(address, "city"));
		
		TextField<String> email = new TextField<String>("email");
		PasswordTextField password = new PasswordTextField("password");
		PasswordTextField repeatPassword = new PasswordTextField("repeat-password");
		
		
		city.setRequired(true);
		email.setRequired(false);
		password.setRequired(false);
		repeatPassword.setRequired(false);
		
		name.add(new SimpleCustomValidator("name")); 
		street.add(new SimpleCustomValidator("street")); 
		zipcode.add(new SimpleCustomValidator("zipcode"));
		city.add(new StringValidator(2, 25)); // alternative: use StringValidator for min/max length validation
		// TODO add validator for email
		password.add(new CustomPasswordValidator()); 
//		repeatPassword.add(new CustomPasswordValidator());
		
		// alternatively use PatternValidator
		repeatPassword.add(new PatternValidator("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})"));
		
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
