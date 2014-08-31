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
	private RelativeLayout.LayoutParams relativeParams;
	
	public ItemRelativeLayout(Context context) {
		super(context);
		
		buildParentLinearParams();
		resetRelativeParams();
		buildName(context);
		buildPrice(context);
		buildQty(context);
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
		itemName.setImeOptions(EditorInfo.IME_ACTION_NEXT);
		itemName.setMaxLines(1);
		itemName.setHint("item name");
		this.addView(itemName);
	}//buildName
	
	private void buildPrice(Context context) {
		Log.e("Spendy", "Building Price");
		//Quantity
		itemPrice = new EditText(context);
		resetRelativeParams();
		relativeParams.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		itemPrice.setLayoutParams(relativeParams);
		itemPrice.setId(111);
		itemPrice.setHint("$");
		this.addView(itemPrice);
	}//buildPrice
	
	private void buildQty(Context context) {
		Log.e("Spendy", "Building Quantity");
		itemQty = new EditText(context);
		resetRelativeParams();
		relativeParams.addRule(RelativeLayout.LEFT_OF, itemPrice.getId());
		itemQty.setLayoutParams(relativeParams);
		itemQty.setHint("quantity");
		this.addView(itemQty);
	}//buildQty
	
	private void resetRelativeParams() {
		relativeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
	}//resetRelativeParams
}//ItemRelativeLayout
