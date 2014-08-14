package com.kevinpedronan.spendy;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

//TODO OUTDATED activity
public class SpendyActivity extends Activity {
	//Declare UI elements
	private Button newTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_spendy);
		
		//Populate newTransaction button
		newTransaction = (Button)findViewById(R.id.new_transaction_button);
		
		//Attach a listener
		newTransaction.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//Start a new activity by passing an intent
				Intent i = new Intent(SpendyActivity.this, TransactionActivity.class);
				startActivity(i);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.spendy, menu);
		return true;
	}

}
