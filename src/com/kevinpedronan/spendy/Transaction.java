package com.kevinpedronan.spendy;

import java.text.SimpleDateFormat;
import java.util.Date;

//OUTDATED 10/29/2014
//import java.text.DecimalFormat;
//import java.text.NumberFormat;

public class Transaction {
	private String transactionName;
	private double total;
	private int numSplit;
	private String sender;
	private String recipient;
	//TODO: Data structure of Items and Persons
	//HashMap Person -> Item
	
	//Empty constructor (if user doesn't enter a transaction name)
	Transaction() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date date = new Date();
		
		this.transactionName = dateFormat.format(date) + " Transaction";
	}
	
	Transaction(double amount) {
		this.total = amount;
	}//double constructor
	
	Transaction(double total, int numSplit) {
		this.total = total;
		this.numSplit = numSplit;
	}//double, int constructor
	
	Transaction(String transactionName, double total, int numSplit) {
		this.transactionName = transactionName;
		this.total = total;
		this.numSplit = numSplit;
	}//double, int constructor
	
	public double getTotal() {
		return 1;
	}//getTotal
	
	public void setTransactionName(String transactionName) {		
		this.transactionName = transactionName;
	}//setTransactionName
	
	public void setSender(String sender) {
		this.sender = sender;
	}//setSender
	
	public void setRecipient(String recipient){
		this.recipient = recipient;
	}//setRecipient
	
	public String split() {
		total = total/numSplit;

		//Convert to StringBuilder
		StringBuilder result_string = new StringBuilder("$" + Double.toString(total));
		//Ensure two decimal precision with zeros
		if(result_string.length() - result_string.substring(0, result_string.indexOf(".")).length() < 3) {
			result_string.append("0");
		}
		
		return result_string.substring(0, result_string.indexOf(".") + 3);
	}//split
	
	public String amountToString() {
		return Double.toString(total);
	}//amountToString
	
	public String numSplitToString() {
		return Double.toString(numSplit);
	}//numSplitToString
	
	public String getTransactionName() {
		return transactionName;
	}//getTransactionName
	
	public int getNumSplit() {
		return numSplit;
	}//getNumSplit
	
	public String getSender() {
		return sender;
	}//getSender
	
	public String getRecipient() {
		return recipient;
	}//getRecipient

//	OUTDATED 10/29/2014
//	public String getAmountString() {
//		DecimalFormat df = new DecimalFormat("#,###,##0.00");
//		return df.format(amount);
//	}//getAmountString
//	
//	public String getNumSplitString() {
//		return numSplit + "";
//	}//getNumSplitString
//	
//	public String viewableAmount() {
//		NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
//		
//		/*
//		 * double money = 100.1;
//			NumberFormat formatter = NumberFormat.getCurrencyInstance();
//			String moneyString = formatter.format(money);
//			System.out.println(moneyString);
//		 */
//		
//		StringBuilder stringBuilderAmount = new StringBuilder(Double.toString(amount));//Case: Dollar precision, e.g. 9, 965
//		if(stringBuilderAmount.indexOf(".") == -1) {
//			stringBuilderAmount.append(".00");
//		}
//		
//		//Case: Dollar precision, e.g. 9. or 99.
//		if((stringBuilderAmount.charAt(stringBuilderAmount.length() - 1) + "").equals(".")) {
//			stringBuilderAmount.append("00");
//		}
//
//		//Case: First decimal precision, e.g. 9.9 or 99.9
//		if((stringBuilderAmount.charAt(stringBuilderAmount.length() - 2) + "").equals(".")) {
//			if(stringBuilderAmount.substring(stringBuilderAmount.indexOf("."), stringBuilderAmount.length()).length() < 3) {
//				stringBuilderAmount.append("0");
//			}
//		}
//		
//		//Case: Preceding 0s, e.g. 09, 009
//		while(true) {
//			if(stringBuilderAmount.charAt(0) == '0')
//				stringBuilderAmount.deleteCharAt(0);
//			else 
//				break;
//		}//while
//		
//		return "$" + stringBuilderAmount.toString();
//	}//viewableAmount
}//Transaction
	
