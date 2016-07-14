package com.gfu.wicket.cheesr.pages;

import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;

import com.gfu.wicket.cheesr.bo.Address;
import com.gfu.wicket.cheesr.bo.Cart;
import com.gfu.wicket.cheesr.components.ShoppingCartPanel;

public class Checkout extends CheesrPage {
	private static final long serialVersionUID = 1L;

	public Checkout() {
		add(new ShoppingCartPanel("shoppingcart", Model.of(getCart())));

		Form<Address> form = new Form<Address>("form");
		add(form);
		Address address = getCart().getBillingAddress();
		form.add(new TextField<String>("name", new PropertyModel<String>(address, "name")).setRequired(true));
		form.add(new TextField<String>("street", new PropertyModel<String>(address, "street")).setRequired(true));
		form.add(new TextField<String>("zipcode", new PropertyModel<String>(address, "zipcode")).setRequired(true));
		form.add(new TextField<String>("city", new PropertyModel<String>(address, "city")).setRequired(true));

		add(new FeedbackPanel("feedback"));
		
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
				 Cart cart = getCart();
				 cart.getCheeses().clear();
				 setResponsePage(Index.class);
			};
		});
	
	}
}
