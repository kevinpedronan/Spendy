/* TransactionActivity: main activity in app
 * _ET: EditText
 * _TV: TextView
 * _LL: LinearLayout
 * _B: Button
 */

package com.kevinpedronan.spendy;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

import com.kevinpedronan.spendy.VenmoLibrary.VenmoResponse;

public class TransactionActivity extends Activity {
	Typeface title_TF;
	LinearLayout.LayoutParams linearParams;
	
	//Transaction Name
	private TextView nameTitle_TV;
	private EditText transactionName_ET;
	
	//Items
	private TextView itemsTitle_TV;
	private LinearLayout itemContainer_LL;
	private ArrayList<Item> items_AL;
	private Button addItem_B;
	private int itemCounter = 222;
	
	//People
	private TextView peopleTitle_TV;
	private LinearLayout personContainer_LL;
	private ArrayList<Person> people_AL;
	private Button addPerson_B;
	private int personCounter = 333;
	
	private EditText amountEditText;
	private EditText numSplitEditText;
	private EditText venmoLoginEditText;
	private TextView resultTextView;
	private Button payButton;

	//Model elements
	private Transaction transaction;
	
	private double amount;
	private int numSplit;
	private String recipient;
	private boolean validAmount = false;
	
	int idStart = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_transaction);
		
		//Reference TypeFaces
		refTF();
		
		//Build UI components
		buildInfoSection();
		buildItemSection();
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
	
	//Reference the Roboto Medium typeface to be used for the activity
	private void refTF() {
		title_TF = Typeface.createFromAsset(getAssets(), "Roboto/Roboto-Medium.ttf");
	}//refTF
	
	private void buildInfoSection() {
		
		//Find the "Name of Transaction" title and set the typeface
		nameTitle_TV = (TextView)findViewById(R.id.name_title_TV);
		nameTitle_TV.setTypeface(title_TF);
		
		//Find the transaction name field
		transactionName_ET = (EditText)findViewById(R.id.transaction_name_ET);
	}//buildInfoSection
	
	private void buildItemSection() {
		transaction = new Transaction();
		
		//Container for section, holds all the Items 
		itemContainer_LL = (LinearLayout)findViewById(R.id.item_container_LL);
		
		//Reset and apply simple match parent linear layout for the container
		resetLinearParams();
		itemContainer_LL.setLayoutParams(linearParams);
				
		//ArrayList to keep track of Items
		items_AL = new ArrayList<Item>();
		
		//Find the "Items" title and set the typeface
		itemsTitle_TV = (TextView)findViewById(R.id.items_title_TV);
		itemsTitle_TV.setTypeface(title_TF);
		
		//Button that adds new Items, (+ button)
		addItem_B = (Button)findViewById(R.id.add_item_B);
		addItem_B.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addItem();
			}
		});
		
		//Add the initial item
		addItem();
	}//buildItemSection
	
	private void addItem() {
		//Create the new Item and set its ID
		Item item = new Item(this);
		item.setId(itemCounter++);
		
		item.itemPrice.addTextChangedListener(new TextWatcher() {

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Debug itemCounter
		//iRL.itemName.setText(Integer.toString(iRL.getId()));
		
		//Add the Item to the ArrayList and the container
		items_AL.add(item);
		itemContainer_LL.addView(item);
	}//addItem
	
	private void buildPeopleSection() {
		//Container for section, holds all the Items 
		personContainer_LL = (LinearLayout)findViewById(R.id.person_container_LL);
				
		//Reset and apply simple match parent linear layout for the container
		resetLinearParams();
		personContainer_LL.setLayoutParams(linearParams);
		
		//ArrayList to keep track of Items
		people_AL = new ArrayList<Person>();

		//Find the "People Splitting" title and set the typeface
		peopleTitle_TV = (TextView)findViewById(R.id.people_title_TV);
		peopleTitle_TV.setTypeface(title_TF);
		
		//Button that adds new Items, (+ button)
		addPerson_B = (Button)findViewById(R.id.add_person_B);
		addPerson_B.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				addPerson();
			}
		});
		
		//Add the initial person
		addPerson();
	}//buildPeopleSection
	
	private void addPerson() {
		
		//Create the new Item and set its ID
		Person person = new Person(this);
		person.setId(personCounter++);
		
		//Debug itemCounter
		//iRL.itemName.setText(Integer.toString(iRL.getId()));
		
		//Add the Item to the ArrayList and the container
		people_AL.add(person);
		personContainer_LL.addView(person);
	}//addPerson
	
	private void resetLinearParams() {
		
		//Define and apply simple match parent linear layout for a container (for Items and People)
		linearParams = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
	}//resetRelativeParams
	
	//TODO: Create listener for for focus is off of price to update transaction figures
	
	
	/*
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
	*/
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
			Intent venmoIntent = VenmoLibrary.openVenmoPayment("1765", "Spendy", recipient, String.valueOf(transaction.getTotal()), "hello", "pay"); 
			Log.e("submitPayment()", String.valueOf(transaction.getTotal()));
			startActivityForResult(venmoIntent, 1);
		} 
		//Venmo native app not install on device, so let's instead open a mobile web version of Venmo in a WebView 
		catch (android.content.ActivityNotFoundException e) { 
			Log.e("submitPayment()", "submitting via WebView");
			Intent venmoIntent = new Intent(TransactionActivity.this, VenmoWebViewActivity.class); 
			String venmo_uri = VenmoLibrary.openVenmoPaymentInWebView("1765", "Spendy", recipient, String.valueOf(transaction.getTotal()), "hello", "pay"); 
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
