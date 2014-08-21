package com.kevinpedronan.spendy;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class Transaction {
	private double amount;
	private int numSplit;
	private String sender;
	private String recipient;
	
	Transaction(double amount) {
		this.amount = amount;
	}//double constructor
	
	Transaction(double amount, int numSplit) {
		this.amount = amount;
		this.numSplit = numSplit;
	}//double, int constructor
	
	public String split() {
		amount = amount/numSplit;

		//Convert to StringBuilder
		StringBuilder result_string = new StringBuilder("$" + Double.toString(amount));
		//Ensure two decimal precision with zeros
		if(result_string.length() - result_string.substring(0, result_string.indexOf(".")).length() < 3) {
			result_string.append("0");
		}
		
		return result_string.substring(0, result_string.indexOf(".") + 3);
	}//split
	
	public double getAmount() {
		return amount;
	}//getAmount
	
	public String amountToString() {
		return Double.toString(amount);
	}//amountToString
	
	public String numSplitToString() {
		return Double.toString(numSplit);
	}//numSplitToString
	
	public int getNumSplit() {
		return numSplit;
	}//getNumSplit
	//TODO: Format currency using java.text
	public String getAmountString() {
		DecimalFormat df = new DecimalFormat("#,###,##0.00");
		return df.format(amount);
	}//getAmountString
	
	public String getNumSplitString() {
		return numSplit + "";
	}//getNumSplitString
	
	public String viewableAmount() {
		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
		
		/*
		 * double money = 100.1;
NumberFormat formatter = NumberFormat.getCurrencyInstance();
String moneyString = formatter.format(money);
System.out.println(moneyString);
		 */
		
		StringBuilder stringBuilderAmount = new StringBuilder(Double.toString(amount));//Case: Dollar precision, e.g. 9, 965
		if(stringBuilderAmount.indexOf(".") == -1) {
			stringBuilderAmount.append(".00");
		}
		
		//Case: Dollar precision, e.g. 9. or 99.
		if((stringBuilderAmount.charAt(stringBuilderAmount.length() - 1) + "").equals(".")) {
			stringBuilderAmount.append("00");
		}

		//Case: First decimal precision, e.g. 9.9 or 99.9
		if((stringBuilderAmount.charAt(stringBuilderAmount.length() - 2) + "").equals(".")) {
			if(stringBuilderAmount.substring(stringBuilderAmount.indexOf("."), stringBuilderAmount.length()).length() < 3) {
				stringBuilderAmount.append("0");
			}
		}
		
		//Case: Preceding 0s, e.g. 09, 009
		while(true) {
			if(stringBuilderAmount.charAt(0) == '0')
				stringBuilderAmount.deleteCharAt(0);
			else 
				break;
		}//while
		
		return "$" + stringBuilderAmount.toString();
	}//viewableAmount
}//Transaction
	
