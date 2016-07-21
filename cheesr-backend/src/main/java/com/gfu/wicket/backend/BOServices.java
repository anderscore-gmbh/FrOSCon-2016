package com.gfu.wicket.backend;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.annotation.ManagedBean;
import javax.management.MXBean;

import com.gfu.wicket.backend.bo.Cart;
import com.gfu.wicket.backend.bo.Cheese;
@ManagedBean
public class BOServices {
	
	private final List<Cheese> cheeses = Arrays.asList(
			new Cheese("Gouda", "Gouda is a yellowish Dutch[...]",
					 1.65),
					new Cheese("Edam", "Edam (Dutch Edammer) is a D[...]",
					 1.05),
					new Cheese("Maasdam", "Maasdam cheese is a Dutc[...]",
					 2.35),
					new Cheese("Brie", "Brie is a soft cows' milk c[...]",
					 3.15),
					new Cheese("Buxton Blue", "Buxton Blue cheese i[...]",
					 0.99),
					new Cheese("Parmesan", "Parmesan is a grana, a [...]",
					 1.99),
					new Cheese("Cheddar", "Cheddar cheese is a hard[...]",
					 2.95),
					new Cheese("Roquefort", "Roquefort is a ewe's-m[...]",
					 1.67),
					new Cheese("Boursin", "Boursin Cheese is a soft[...]",
					 1.33),
					new Cheese("Camembert", "Camembert is a soft, c[...]",
					 1.69),
					new Cheese("Emmental", "Emmental is a yellow, m[...]",
					 2.39),
					new Cheese("Reblochon", "Reblochon is a French [...]",
					 2.99));
	
	/**
	 * Ermittet alle verfuegbaren Kaesesorten
	 * @return - Liste aller Sorten
	 */
	public List<Cheese> allCheeses(){
		return Collections.unmodifiableList(cheeses);
	}
	
	/**
	 * Uebergibt eine Bestellung zur Abwicklung ins Backend-System
	 * @param cart
	 */
	public void proccessOrder(Cart cart){
		// Nichts passiert - das ist im Beispiel so. - Loggen:
		System.out.println(">>>> Start: Bearbeite Bestellung <<<<<");
		System.out.println(cart.getBillingAddress().toString());
		for(Cheese c : cart.getCheeses()){
			System.out.println(c.toString());
		}
		System.out.println(">>>> Ende: Bearbeite Bestellung <<<<<");
		
	}

	public static BOServices get(){
		return new BOServices();
	}
}
