package com.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.gfu.wicket.cheesr.webapp.WicketApplication;
import com.gfu.wicket.cheesr.webapp.pages.Checkout;
import com.gfu.wicket.cheesr.webapp.pages.Index;

public class TestIndexPage{
	
	private WicketTester tester;
	
	@Before
	public void setUp(){
		tester = new WicketTester(new WicketApplication());
		
		//start and render the test page
		tester.startPage(Index.class);
	}
	
	@Test
	public void testIndexPageRendered(){
		
		//assert rendered page class
		tester.assertRenderedPage(Index.class);
	}
	
	@Test
	public void testCheeseList() {
		
		// assert list component and values
		tester.assertComponent("cheeses", ListView.class);
		tester.assertModelValue("cheeses:0:name", "Gouda");
		tester.assertLabel("cheeses:0:name", "Gouda");
		
		tester.assertComponent("cheeses", ListView.class);
		tester.assertModelValue("cheeses:1:name", "Edam");
		tester.assertLabel("cheeses:1:name", "Edam");
		
		tester.assertComponent("cheeses", ListView.class);
		tester.assertModelValue("cheeses:2:name", "Maasdam");
		tester.assertLabel("cheeses:2:name", "Maasdam");
		
		// ... TODO add more cheese
	}
	
	@Test
	public void testClickCheckoutLink() {
	
		// assert the start page rendered
		tester.assertRenderedPage(Index.class);
		
		// assert checkout link only visible after cheese has been added
		tester.assertInvisible("checkout");
		tester.clickLink("cheeses:0:add");
		tester.assertVisible("checkout");
		
		//simulate a click on "checkout" button
		tester.clickLink("checkout");
		
		//assert new page
		tester.assertRenderedPage(Checkout.class);	
	}
}
