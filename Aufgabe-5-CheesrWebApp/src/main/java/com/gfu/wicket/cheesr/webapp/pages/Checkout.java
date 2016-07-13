package com.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.model.PropertyModel;

import com.gfu.wicket.backend.BOServices;
import com.gfu.wicket.backend.bo.Address;
import com.gfu.wicket.backend.bo.Cart;

public class Checkout extends CheesrPage {

	private static final long serialVersionUID = 1L;

	public Checkout() {
		Form<Address> form = new Form<Address>("form");
		add(form);
		Address address = getCart().getBillingAddress();
		form.add(new TextField<String>("name", new PropertyModel<String>(address, "name")));
		form.add(new TextField<String>("street", new PropertyModel<String>(address, "street")));
		form.add(new TextField<String>("zipcode", new PropertyModel<String>(address, "zipcode")));
		form.add(new TextField<String>("city", new PropertyModel<String>(address, "city")));

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
	}
}
