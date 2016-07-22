package com.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Test;

import com.gfu.wicket.cheesr.webapp.WicketApplication;
import com.gfu.wicket.cheesr.webapp.pages.Checkout;

public class TestCheckoutPage{
	
	private WicketTester tester;
	
	@Before
	public void setUp(){
		tester = new WicketTester(new WicketApplication());
		//start and render the test page
		tester.startPage(Checkout.class);
	}
	
	@Test
	public void testCheckoutPageRendered() {
		
		//assert rendered page class
		tester.assertRenderedPage(Checkout.class);
	}
	
	@Test
	public void testFeedbackPanel(){
		
		tester.assertRenderedPage(Checkout.class);
		tester.assertComponent("form", Form.class);
		tester.assertComponent("form:feedback", FeedbackPanel.class);
		tester.assertNoInfoMessage();
		tester.assertNoErrorMessage();
	}
	
	
}
