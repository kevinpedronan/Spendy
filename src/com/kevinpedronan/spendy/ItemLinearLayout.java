package com.kevinpedronan.spendy;

import android.content.Context;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;

public class ItemLinearLayout extends LinearLayout {
	public EditText itemName;
	public EditText itemQty;
	public EditText itemPrice;

	public ItemLinearLayout(Context context) {
		super(context);
		
		this.setOrientation(LinearLayout.HORIZONTAL);
		
		//Instantiate elements within the layout
		itemName = new EditText(context);
		itemQty = new EditText(context);
		itemPrice = new EditText(context);
		
		//Create unique IDs based on the calling activity's local value
		//Will be used to iterate through elements to capture data
		//TODO: Fix new line creation instead of ACTION_NEXT
		itemName.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		itemName.setMaxLines(1);
		itemName.setHint("item name");
		
		itemQty.setHint("quantity");
		
		itemPrice.setHint("$");
		
		this.addView(itemName);
		this.addView(itemQty);
		this.addView(itemPrice);		
	}//constructor

}
