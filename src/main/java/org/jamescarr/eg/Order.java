package org.jamescarr.eg;

public class Order {
	private final String name;
	private final Double amount;
	public Order(String name, Double amount) {
		this.name = name;
		this.amount = amount;
	}
	public String getName() {
		return name;
	}
	public Double getAmount() {
		return amount;
	}
	
}
