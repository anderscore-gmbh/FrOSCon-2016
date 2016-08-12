package com.gfu.wicket.cheesr.webapp.pages;

import org.apache.wicket.util.tester.FormTester;
import org.apache.wicket.util.tester.WicketTester;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.gfu.wicket.cheesr.webapp.WicketApplication;

public class CheckoutFormTest {
	
	private WicketTester tester;
	private FormTester formTester;

	@Before
	public void setUp() {
		tester = new WicketTester(new WicketApplication());
		tester.startPage(Index.class);
		
		// Fill shopping cart and switch to checkout page
		
		tester.clickLink("cheeses:0:add");
		tester.clickLink("cheeses:1:add");
		tester.clickLink("checkout");
		
		// Fill checkout form
		
	    formTester = tester.newFormTester("form");
	    
	    formTester.setValue("name", "Max Mustermann");
	    formTester.setValue("street", "Musterstr. 123");
	    formTester.setValue("zipcode", "12345");
	    formTester.setValue("city", "Musterstadt");
//	    formTester.setValue("email", "max.mustermann@mail.com");
	}
	
	@Test
	public void testFields(){
		
		tester.assertRequired("form:name");
		tester.assertRequired("form:street");
		tester.assertRequired("form:zipcode");
		tester.assertRequired("form:city");
	}
	
	@Test
	public void testOrderCancelled(){
		
		tester.clickLink("form:cancel");
		tester.assertRenderedPage(Index.class);
	}
	
	@Test
	public void testOrderFailure(){
		
		formTester.setValue("city", null);
		formTester.setValue("street", null);
	    formTester.submit();
	    tester.assertErrorMessages("Bitte tragen Sie einen Wert im Feld 'city' ein.",
	    		"Bitte tragen Sie einen Wert im Feld 'street' ein.");
	}
	
	@Test
	public void testOrderSuccess(){
	   
	    formTester.submit();
	    tester.assertNoErrorMessage();
	}
	
	@Test
	public void testPasswordNotEqual(){
	    formTester.setValue("password", "DummyPassword01");
	    formTester.setValue("repeat-password", "DummyPassword02");
	    formTester.submit();
	    
	    tester.assertErrorMessages("password und repeat-password müssen gleich sein.");
	}
	
	@Test
	public void testValidPasswordPattern() {
		
		String validPassword = "DummyPassword01";
		formTester.setValue("password", validPassword);
		formTester.setValue("repeat-password", validPassword);
		formTester.submit();
		
		tester.assertNoErrorMessage();
	}
	
	@Test
	public void testWrongPasswordPattern() {
		
		String wrongPassword = "wrongpassword";
		formTester.setValue("password", wrongPassword);
		formTester.setValue("repeat-password", wrongPassword);
		formTester.submit();
		
		tester.assertErrorMessages(
				"\"Password\" muss 6-20 Zeichen lang sein, mit mind. einem Groß- und Kleinbuchstaben und einer Zahl",
				"\"Password (repeat)\" muss 6-20 Zeichen lang sein, mit mind. einem Groß- und Kleinbuchstaben und einer Zahl");
	}
	
	// TODO test email, test password set when email set...
	
	
}