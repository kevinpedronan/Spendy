package com.kevinpedronan.spendy;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ItemRelativeLayout extends RelativeLayout {
	public EditText itemName;
	public EditText itemQty;
	public EditText itemPrice;
	
	public ItemRelativeLayout(Context context) {
		super(context);
		this.setBackgroundColor(Color.parseColor("#e3e3e3"));
		
		Log.e("Spendy", "ItemRelativeLayout constructor");
		//Set LinearLayout parameters for parent element
		LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.setLayoutParams(linearParams);
		
		Log.e("Spendy", "Building Name");
		//Name
		//TODO: Fix new line creation instead of ACTION_NEXT
		itemName = new EditText(context);
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		itemName.setLayoutParams(relativeParams);
		itemName.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		itemName.setMaxLines(1);
		itemName.setHint("item name");
		this.addView(itemName);
		
		Log.e("Spendy", "Building Quantity");
		//Quantity
		itemPrice = new EditText(context);
		relativeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		itemPrice.setLayoutParams(relativeParams);
		itemPrice.setId(111);
		itemPrice.setHint("$");
		this.addView(itemPrice);
		
		Log.e("Spendy", "Building Amount");
		//Price
		itemQty = new EditText(context);
		relativeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		relativeParams.addRule(RelativeLayout.LEFT_OF, itemPrice.getId());
		itemQty.setLayoutParams(relativeParams);
		itemQty.setHint("quantity");
		this.addView(itemQty);
	}//constructor
}//ItemRelativeLayout
