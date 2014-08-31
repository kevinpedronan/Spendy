package com.kevinpedronan.spendy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

import com.kevinpedronan.spendy.VenmoLibrary.VenmoResponse;

public class TransactionActivity extends Activity {
	//Transaction Name
	private TextView nameTitleTextView;
	private EditText transactionNameET;
	
	//Items
	private TextView itemsTitleTextView;
	private LinearLayout itemContainer;
	private ArrayList<ItemLinearLayout> items;
	private ArrayList<ItemRelativeLayout> itemsR;
	private Button addItem_B;
	private int itemCounter = 222;
	
	//People
	private TextView peopleTitleTextView;
	
	Typeface title_TF;
	
	private EditText amountEditText;
	private EditText numSplitEditText;
	private EditText venmoLoginEditText;
	private TextView resultTextView;
	private Button payButton;

	
	//Declare Model elements
	private double amount;
	private int numSplit;
	private String recipient;
	private Transaction transaction;
	private boolean validAmount = false;
	
	int idStart = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		
		//Reference TypeFaces
		refTF();
		
		buildInfoSection();
		//buildItemSection();
		buildItemRelativeSection();
		buildPeopleSection();
		
		/*
		resultTextView = (TextView)findViewById(R.id.result_text_view);
		buildAmountEditText();
		buildNumSplitEditText();
		buildVenmoLoginEditText();
		buildPayButton();*/
	}//onCreate
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.transaction, menu);
		return true;
	}//onCreateOptionsMenu
	
	public void refTF() {
		title_TF = Typeface.createFromAsset(getAssets(), "Roboto/Roboto-Medium.ttf");
	}//refTF
	
	public void buildInfoSection() {
		nameTitleTextView = (TextView)findViewById(R.id.name_title);
		nameTitleTextView.setTypeface(title_TF);
		
		transactionNameET = (EditText)findViewById(R.id.transaction_name_ET);
	}//buildInfoSection
	
	public void buildItemSection() {
		//UI container for section
		itemContainer = (LinearLayout)findViewById(R.id.item_container);
				
		//Title
		itemsTitleTextView = (TextView)findViewById(R.id.items_title);
		itemsTitleTextView.setTypeface(title_TF);
	
		//Add item Button
		addItem_B = (Button)findViewById(R.id.add_item_button);
		addItem_B.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addItem();
			}
		});
		
		//ArrayList to hold Items
		items = new ArrayList<ItemLinearLayout>();
		
		addItem();
	}//buildItemSection
	
	public void addItem() {
		ItemLinearLayout ill = new ItemLinearLayout(this);
		ill.setId(itemCounter);
		items.add(ill);
		itemContainer.addView(ill);
		itemCounter++;
	}//addItem
	
	public void buildItemRelativeSection() {
		//UI container for section
		itemContainer = (LinearLayout)findViewById(R.id.item_container);
		LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		itemContainer.setLayoutParams(linearParams);
				
		//ArrayList to hold Items
		itemsR = new ArrayList<ItemRelativeLayout>();
		
		//Title
		itemsTitleTextView = (TextView)findViewById(R.id.items_title);
		itemsTitleTextView.setTypeface(title_TF);
		
		//Add item Button
		addItem_B = (Button)findViewById(R.id.add_item_button);
		addItem_B.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addRelativeItem();
			}
		});
		
		addRelativeItem();
	}
	
	public void addRelativeItem() {
		ItemRelativeLayout iRL = new ItemRelativeLayout(this);
		iRL.setId(itemCounter++);
		//iRL.itemName.setText(Integer.toString(iRL.getId()));
		itemsR.add(iRL);
		itemContainer.addView(iRL);
	}
	
	public void buildPeopleSection() {
		peopleTitleTextView = (TextView)findViewById(R.id.people_title);
		peopleTitleTextView.setTypeface(title_TF);
	}//buildPeopleSection
	
	public void buildAmountEditText() {
		//Find XML element
		amountEditText = (EditText)findViewById(R.id.amount_edit_text);
		
		//Respond to Next action
		amountEditText.setOnEditorActionListener(new OnEditorActionListener () {
			//TODO Maintain focus on amountEditText element when wrong input is encountered
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				boolean handled = false;
				
				//If Next is selected from keyboard action
				if(actionId == EditorInfo.IME_ACTION_NEXT) {
					parseAmount();
					amountEditText.setText(transaction.viewableAmount());
				}//if
				return handled;
			}//onEditorAction
		});
	}//buildAmountEditText
	
	public void buildNumSplitEditText() {
		//Find XML element
		numSplitEditText = (EditText)findViewById(R.id.num_split_edit_text);

		//Respond to Next action
		numSplitEditText.setOnEditorActionListener(new OnEditorActionListener () {
			//TODO Maintain focus on amountEditText element when wrong input is encountered
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				boolean handled = false;
				
				//If Next is selected from keyboard action
				if(actionId == EditorInfo.IME_ACTION_NEXT) {
					parseNumSplit();
				}//if
				return handled;
			}//onEditorAction
		});
		/*
		
		*/
	}//buildNumSplitEditText
	
	public void buildVenmoLoginEditText() {
		//Find XML element
		venmoLoginEditText = (EditText)findViewById(R.id.venmo_login);
	
		//Respond to Send action
		venmoLoginEditText.setOnEditorActionListener(new OnEditorActionListener () {
			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				boolean handled = false;
				
				//If Send is selected from keyboard action
				if(actionId == EditorInfo.IME_ACTION_GO) {
					//Get and parse numSplit
					try {
						parseVenmoLogin();
					}
					catch (NumberFormatException e) {
					}//catch NumberFormatException
					
					transaction = new Transaction(amount, numSplit);
					//Hide keyboard
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
					imm.hideSoftInputFromWindow(resultTextView.getApplicationWindowToken(), 0);
					
					//Set result TextView
					resultTextView.setText(transaction.split());
					
					if(validAmount)	
						payButton.setEnabled(true);
					handled = true;
				}//if
				return handled;
			}//onEditorAction
		});
	}//buildVenmoLoginEditText
	
	public void buildPayButton() {
		//Find XML element
		payButton = (Button)findViewById(R.id.pay_button);
		
		//Set payButton as inactive by default
		payButton.setEnabled(false);
		
		payButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				submitPayment();
			}//onClick
		});
	}//buildPayButton
	
	public void parseAmount() {
		StringBuilder stringBuilderAmount;
		stringBuilderAmount = new StringBuilder(amountEditText.getText().toString());
		
		//Ensure amount doesn't not have '$'
		if(stringBuilderAmount.indexOf("$") != -1) {
			stringBuilderAmount.deleteCharAt(0);
		}//if
		
		//Ensure amount is zeroed from previous entries
		//amount = 0.0;
		validAmount = false;
		
		//FIXED cannot throw more than one exception
		while(!validAmount) {
			try {
				Double.parseDouble(stringBuilderAmount.toString());
				validAmount = true;
			}
			catch (NumberFormatException e) {
				Log.e("parseAmount(): ", "NumberFormatException thrown");
				amountEditText.setText("");
				Toast.makeText(TransactionActivity.this, R.string.invalid_amount, Toast.LENGTH_SHORT).show();
				return;
			}
		}//while
		
		amount = Double.parseDouble(stringBuilderAmount.toString());
		transaction = new Transaction(amount);
		
		//TODO: move to Transaction class
		//TODO: find a more efficient way of doing this
		
		return;
	}//parseAmount
	
	public void parseNumSplit() throws NumberFormatException{
		
		while(!validAmount) {
			try {
				Integer.parseInt(numSplitEditText.getText().toString());
				validAmount = true;
			}
			catch (NumberFormatException e) {
				numSplitEditText.setText("");
				Toast.makeText(TransactionActivity.this, R.string.invalid_amount, Toast.LENGTH_SHORT).show();
				return;
			}
		}//while
		numSplit = Integer.parseInt(numSplitEditText.getText().toString());
	}//parseNumSplit
	
	public void parseVenmoLogin() {
		recipient = venmoLoginEditText.getText().toString();
	}//parseVenmoLogin
	
	public void submitPayment() {
		try { 
			//TODO: Remove hard-coded String parameters
			Intent venmoIntent = VenmoLibrary.openVenmoPayment("1765", "Spendy", recipient, transaction.getAmountString(), "hello", "pay"); 
			Log.e("submitPayment()", transaction.getAmountString());
			startActivityForResult(venmoIntent, 1);
		} 
		//Venmo native app not install on device, so let's instead open a mobile web version of Venmo in a WebView 
		catch (android.content.ActivityNotFoundException e) { 
			Log.e("submitPayment()", "submitting via WebView");
			Intent venmoIntent = new Intent(TransactionActivity.this, VenmoWebViewActivity.class); 
			String venmo_uri = VenmoLibrary.openVenmoPaymentInWebView("1765", "Spendy", recipient, transaction.getAmountString(), "hello", "pay"); 
			venmoIntent.putExtra("url", venmo_uri); 
			startActivityForResult(venmoIntent, 1); 
		}
	}//submitPayment()
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
	    switch(requestCode) {
	        case 1: { //1 is the requestCode we picked for Venmo earlier when we called startActivityForResult
	            if(resultCode == RESULT_OK) {
	                String signedrequest = data.getStringExtra("signedrequest");
	                if(signedrequest != null) {
	                    VenmoResponse response = (new VenmoLibrary()).validateVenmoPaymentResponse(signedrequest, R.string.venmo_app_secret + "");
	                    if(response.getSuccess().equals("1")) {
	                        //TODO
	                    	//Payment successful.  Use data from response object to display a success message
	                        String note = response.getNote();
	                        String amount = response.getAmount();
	                    }
	                }
	                else {
	                    String error_message = data.getStringExtra("error_message");
	                    //TODO
	                    //An error occurred.  Make sure to display the error_message to the user
	                }                               
	            }
	            else if(resultCode == RESULT_CANCELED) {
	                //The user cancelled the payment
	            }
	        break;
	        }           
	    }
	}//onActivityForResult
}
