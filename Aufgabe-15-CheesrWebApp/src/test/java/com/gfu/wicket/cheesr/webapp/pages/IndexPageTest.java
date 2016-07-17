package com.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.gfu.wicket.cheesr.webapp.WicketApplication;
import com.gfu.wicket.cheesr.webapp.components.ShoppingCartPanel;

public class IndexPageTest {

	private WicketTester tester;

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
		tester.startPage(Index.class);
	}
	
	@Test
	public void testPage(){
		tester.assertRenderedPage(Index.class);
	}

	@Test
	public void testCheeseList() {
		tester.assertComponent("cheeses", ListView.class);
		tester.assertModelValue("cheeses:0:name", "Gouda");
		tester.assertLabel("cheeses:0:name", "Gouda");
	}
	
	@Test
	public void testCart(){
		tester.assertComponent("cart", ShoppingCartPanel.class);
		tester.assertInvisible("checkout");
		
		tester.clickLink("cheeses:0:add");
		tester.assertVisible("checkout");
	}
}