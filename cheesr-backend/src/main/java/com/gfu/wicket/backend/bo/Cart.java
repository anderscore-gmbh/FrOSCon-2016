package com.gfu.wicket.backend.bo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private List<Cheese> cheeses = new ArrayList<Cheese>();
	
	private UserInfo billingAddress = new UserInfo();
	public List<Cheese> getCheeses() {
		return cheeses;
	}
	public void setCheeses(List<Cheese> cheeses) {
		this.cheeses = cheeses;
	}
	public UserInfo getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(UserInfo billingAddress) {
		this.billingAddress = billingAddress;
	}
	
	
	public double getTotal(){
		double total = 0;
		for(Cheese cheese : cheeses){
			total += cheese.getPrice();
		}
		return total;
	}
}
