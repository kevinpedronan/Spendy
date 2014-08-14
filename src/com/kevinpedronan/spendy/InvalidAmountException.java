package com.kevinpedronan.spendy;

public class InvalidAmountException extends Exception{
	private double amount;
	
	public InvalidAmountException() {
		
	}
	
	public InvalidAmountException(double amount) {
		this.amount = amount;
	}//double constructor
	
	public double getAmount() {
		return amount;	
	}//amount getter
}//InvalidAmountException class
