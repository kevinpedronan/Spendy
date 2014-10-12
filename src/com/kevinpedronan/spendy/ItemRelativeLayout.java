package com.kevinpedronan.spendy;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;

public class ItemRelativeLayout extends RelativeLayout {
	public EditText itemName;
	public EditText itemQty;
	public Spinner itemQtySpinner;
	private ArrayList<Integer> quantities;
	public EditText itemPrice;
	private RelativeLayout.LayoutParams relativeParams;
	
	public ItemRelativeLayout(Context context) {
		super(context);
		
		buildParentLinearParams();
		buildQuantities();
		resetRelativeParams();
		buildName(context);
		buildPrice(context);
		//Outdated buildQty(context);
		buildQtySpinner(context);
	}//constructor
	
	private void buildParentLinearParams() {
		Log.e("Spendy", "ItemRelativeLayout constructor");
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
	
	private void buildQty(Context context) {
		Log.e("Spendy", "Building Quantity");
		itemQty = new EditText(context);
		resetRelativeParams();
		relativeParams.addRule(RelativeLayout.LEFT_OF, itemPrice.getId());
		itemQty.setLayoutParams(relativeParams);
		itemQty.setEms(2);
		itemQty.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
		itemQty.setHint("quantity");
		this.addView(itemQty);
	}//buildQty
	
	private void buildQtySpinner(Context context) {
		Log.e("Spendy", "Building Qty Spinner");
		itemQtySpinner = new Spinner(context);
		ArrayAdapter<Integer> qtyAdapter = new ArrayAdapter<Integer>(context, android.R.layout.simple_spinner_item, quantities);
		itemQtySpinner.setAdapter(qtyAdapter);
		resetRelativeParams();
		relativeParams.addRule(RelativeLayout.LEFT_OF, itemPrice.getId());
		relativeParams.addRule(RelativeLayout.ALIGN_BASELINE, itemPrice.getId());
		itemQtySpinner.setLayoutParams(relativeParams);
		this.addView(itemQtySpinner);
	}
	
	private void buildQuantities() {
		quantities = new ArrayList<Integer>();
		for(int i=1;i<11;i++) {
			quantities.add(i);
		}
	}//buildQuantities
	
	private void resetRelativeParams() {
		relativeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	}//resetRelativeParams
}//ItemRelativeLayout
