package com.gfu.wicket.backend;

import static org.junit.Assert.*;

import org.junit.Test;

import com.gfu.wicket.backend.bo.Cart;

public class BOServiceTest {

	private BOServices services = new BOServices();
	
	@Test
	public void testAllCheesesOk() {
		assertEquals(12, services.allCheeses().size());
	}

	@Test
	public void testOrderPlace(){
		services.proccessOrder(new Cart());
		assertTrue(true); // Kein Fehler, alles ok
	}
}
