package com.kevinpedronan.spendy;

import android.content.Context;
import android.widget.EditText;
import android.widget.RelativeLayout;

public class ItemLayout extends RelativeLayout{
	public EditText itemName;
	public EditText itemQty;
	public EditText itemPrice;
	
	public ItemLayout(Context context, int idStart) {
		super(context);
	
		//Instantiate elements within the relative layout
		itemName = new EditText(context);
		itemQty = new EditText(context);
		itemPrice = new EditText(context);
		
		//Create unique IDs based on the calling activity's local value
		//Will be used to iterate through elements to capture data
		itemName.setId(idStart++);
		itemQty.setId(idStart++);
		itemPrice.setId(idStart++);
		
		//Align the itemName field to left of the parent
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, 
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		itemName.setLayoutParams(params);
		
		//Align the itemQty field to right of itemName
		params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.RIGHT_OF, itemName.getId());
		itemQty.setLayoutParams(params);
		
		//Align the addItem button the right of the parent
		params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
		itemPrice.setLayoutParams(params);
	}//constructor

}
