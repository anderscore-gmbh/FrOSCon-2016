package com.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.WebPage;

import com.gfu.wicket.backend.bo.Cart;
import com.gfu.wicket.cheesr.webapp.AuthenticatedWebPage;
import com.gfu.wicket.cheesr.webapp.CheesrSession;

public abstract class CheesrPage extends WebPage {
	private static final long serialVersionUID = 1L;

	public Cart getCart(){
		return ((CheesrSession)getSession()).getCart();
	}
}
