package com.gfu.wicket.cheesr.webapp.pages;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.Model;
import org.apache.wicket.util.tester.TagTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.gfu.wicket.backend.bo.Address;
import com.gfu.wicket.backend.bo.Cart;
import com.gfu.wicket.backend.bo.Cheese;
import com.gfu.wicket.cheesr.webapp.WicketApplication;
import com.gfu.wicket.cheesr.webapp.components.ShoppingCartPanel;

public class ShoppingCartPanelTest {
	
	private WicketTester tester;
	private Cart cart;

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
		
		Address address = new Address();
		address.setName("Max Mustermann");
		address.setStreet("Musterstr. 123");
		address.setZipcode("12345");
		address.setCity("Musterstadt");
		
		List<Cheese> cheeses = new ArrayList<>();
		cheeses.add(new Cheese("Gouda", "Dutch Cheese", 1.33));
		cheeses.add(new Cheese("Edam", "Fine Edam cheese", 0.65));
		
		cart = new Cart();
		cart.setBillingAddress(address);
		cart.setCheeses(cheeses);
		
		ShoppingCartPanel panel = new ShoppingCartPanel("panel", new Model<>(cart));	
		tester.startComponentInPage(panel);
	}
	
	@Test
	public void testEntries(){
		tester.assertComponent("panel:cart", ListView.class);
		
		tester.assertLabel("panel:cart:0:name", "Gouda");
		tester.assertLabel("panel:cart:0:price", "$ 1.33");
		tester.assertComponent("panel:cart:0:remove", Link.class);
		
		tester.assertLabel("panel:cart:1:name", "Edam");
		tester.assertLabel("panel:cart:1:price", "$ 0.65");
		tester.assertComponent("panel:cart:1:remove", Link.class);
		
		String expectedTotal = (cart.getTotal() + " €").replace('.', ',');
		tester.assertLabel("panel:total", expectedTotal);
	}
	
	@Test
	public void testMarkup(){
	    String responseTxt = tester.getLastResponse().getDocument();
	    
	    List<TagTester> tagTesters = TagTester.createTagsByAttribute(responseTxt, "class", "item", false);
	    assertEquals(2, tagTesters.size());
	    
	    TagTester tagTester = TagTester.createTagByAttribute(responseTxt, "class", "total");
	    assertNotNull(tagTester);
	    assertEquals("tr", tagTester.getName());
	}
	
	@Test
	public void testRemove(){
		assertEquals(2, cart.getCheeses().size());
		
		tester.clickLink("panel:cart:0:remove");
		
		tester.assertContainsNot("panel:cart:1:name");
		tester.assertLabel("panel:cart:0:name", "Edam");
		
		assertEquals(1, cart.getCheeses().size());
	}
}
