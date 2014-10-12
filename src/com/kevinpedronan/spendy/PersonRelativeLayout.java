package com.kevinpedronan.spendy;

import android.content.Context;
import android.util.Log;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class PersonRelativeLayout extends RelativeLayout {
	public EditText personName;
	
	public PersonRelativeLayout(Context context) {
		super(context);
		
		buildParentLinearParams();
		buildName(context);
	}//constructor
	
	//Defines the LinearLayout parameters for the parent that will contain this custom Layout
	private void buildParentLinearParams() {
		Log.e("Spendy", "ItemRelativeLayout constructor");
		//Set LinearLayout parameters for parent element
		LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.setLayoutParams(linearParams);
	}//buildParentLinearParams
	
	//Context is needed to pass to EditText constructor
	private void buildName(Context context) {
		personName = new EditText(context);
		
		//Create a new RelativeLayout parameter to apply to the personName EditText
		RelativeLayout.LayoutParams relativeParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
		
		//Define the view to align to the PersonRelativeLayout's left
		relativeParams.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
		
		//Apply the layout parameters to the personName View
		personName.setLayoutParams(relativeParams);
		
		//Set text size, (em)
		personName.setEms(7);
		
		//Define the input type to be simple text
		personName.setInputType(EditorInfo.TYPE_CLASS_TEXT);
	}
}//PersonRelativeLayout
