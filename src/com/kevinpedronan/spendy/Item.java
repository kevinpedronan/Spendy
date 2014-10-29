package com.kevinpedronan.spendy;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class Item extends RelativeLayout {
	
	//Public so Activity can attach listeners and handle result
	public EditText itemName;
	public Spinner itemQtySpinner;
	public EditText itemPrice;
	public int quantity;
	private ArrayList<Integer> quantities;
	private RelativeLayout.LayoutParams relativeParams;
	
//	OUTDATED 10/29/2014: Using Spinner instead of EditText
//	public EditText itemQty;
	
	public Item(Context context) {
		super(context);
		
		Log.e("Spendy", "Item constructor");
		buildParentLinearParams();
		buildQuantities();
		resetRelativeParams();
		buildName(context);
		buildPrice(context);
//		OUTDATED 10/29/2014: buildQty(context);
		buildQtySpinner(context);
	}//constructor
	
	private void buildParentLinearParams() {
		
		//Set LinearLayout parameters for parent element
		LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.setLayoutParams(linearParams);
	}//buildParentLinearParams
	
	private void buildName(Context context) {
		Log.e("Spendy", "Building Name");
		//Name
		//TODO: Fix new line creation instead of ACTION_NEXT
		itemName = new EditText(context);
		resetRelativeParams();
		relativeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		itemName.setLayoutParams(relativeParams);
		//LayoutParams editTextParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		//itemName.setLayoutParams(editTextParams);
		itemName.setEms(7);
		itemName.setInputType(EditorInfo.TYPE_CLASS_TEXT);
		
		//itemName.setMaxLines(1);
		itemName.setHint("item name");
		this.addView(itemName);
	}//buildName
	
	private void buildPrice(Context context) {
		Log.e("Spendy", "Building Price");
		itemPrice = new EditText(context);
		resetRelativeParams();
		relativeParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		itemPrice.setLayoutParams(relativeParams);
		itemPrice.setRawInputType(EditorInfo.TYPE_CLASS_NUMBER | EditorInfo.TYPE_NUMBER_FLAG_DECIMAL);
		itemPrice.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		//Needed for relative layout RIGHT OF
		itemPrice.setId(111);
		itemPrice.setEms(4);
		itemPrice.setHint("$");
		this.addView(itemPrice);
	}//buildPrice
	
//	OUTDATED 10/29/2014
//	private void buildQty(Context context) {
//		Log.e("Spendy", "Building Quantity");
//		itemQty = new EditText(context);
//		resetRelativeParams();
//		relativeParams.addRule(RelativeLayout.LEFT_OF, itemPrice.getId());
//		itemQty.setLayoutParams(relativeParams);
//		itemQty.setEms(2);
//		itemQty.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
//		itemQty.setHint("quantity");
//		this.addView(itemQty);
//	}//buildQty
	
	private void buildQtySpinner(Context context) {
		Log.e("Spendy", "Building Qty Spinner");
		itemQtySpinner = new Spinner(context);
		ArrayAdapter<Integer> qtyAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, quantities);
		itemQtySpinner.setAdapter(qtyAdapter);
		
		//Create the listener for when a quantity is selected 
		itemQtySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
				quantity = (Integer) parent.getItemAtPosition(position);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
		});
		resetRelativeParams();
		relativeParams.addRule(RelativeLayout.LEFT_OF, itemPrice.getId());
		relativeParams.addRule(RelativeLayout.ALIGN_BASELINE, itemPrice.getId());
		itemQtySpinner.setLayoutParams(relativeParams);
		this.addView(itemQtySpinner);
	}
	
	private void buildQuantities() {
		quantities = new ArrayList<Integer>();
		for(int i=1;i < 11;i++) {
			quantities.add(i);
		}
	}//buildQuantities
	
	//Called to reuse layout parameters for building Item
	private void resetRelativeParams() {
		relativeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	}//resetRelativeParams
	
	
	public double computeItemTotal() {
		return Double.parseDouble(itemPrice.getText().toString())* quantity;
	}//computeItemTotal

//	OUTDATED 10/29/2014: User can only enter numbers (no $'s)
//	public void parsePrice(TextView v) {
//		boolean validPrice;
//		
//		StringBuilder stringBuilderAmount = new StringBuilder(v.getText().toString());
//		
//		//Ensure amount doesn't not have '$' (assumes $ is at the beginning of the string)
//		if(stringBuilderAmount.indexOf("$") != -1) {
//			stringBuilderAmount.deleteCharAt(0);
//		}//if
//		
//		//Ensure amount is zeroed from previous entries
//		//amount = 0.0;
//		validPrice = false;
//		
//		//FIXED cannot throw more than one exception
//		while(!validPrice) {
//			try {
//				Double.parseDouble(stringBuilderAmount.toString());
//				validPrice = true;
//			}
//			catch (NumberFormatException e) {
//				Log.e("parseAmount(): ", "NumberFormatException thrown");
//				v.setText("");
//				Toast.makeText(TransactionActivity , R.string.invalid_amount, Toast.LENGTH_SHORT).show();
//				return;
//			}
//		}//while
//		
//		amount = Double.parseDouble(stringBuilderAmount.toString());
//		
//		//TODO: move to Transaction class
//		//TODO: find a more efficient way of doing this
//		
//		return;
//	}
	
}//Item
