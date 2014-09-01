package com.kevinpedronan.spendy;

public class Item {
	private String name;
	private int quantity;
	private double price;
	
	public Item() {
		
	}//constructor
	
	public Item(String name, int qty, double price) {
		this.name = name;
		this.quantity = qty;
		this.price = price;
	}//constructor
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}
}
