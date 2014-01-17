package com.example.avr_keyring;

import classes.AppSettings;
import classes.MainController;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends Activity {

	AppSettings appSets;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		appSets = AppSettings.getInstance();
		context = this.getApplicationContext();
		
		 Button btnDeletePasswords = (Button) findViewById(R.id.btnLogin);
	        btnDeletePasswords.setOnClickListener(new View.OnClickListener() {
	            @Override
	            public void onClick(View v) {
	            	
	            	String pass = ((EditText) findViewById(R.id.editLoginPass)).getText().toString();
	            	//appSets.saveSettings();
	            	if(appSets.checkPassword(pass))
	            	{
	            		Intent intent = new Intent(context, MainActivity.class);
	            		finish();
	            		startActivity(intent);	
	            	}
	            	else
	            	{
	            		Toast.makeText(context,"Invalid password", Toast.LENGTH_SHORT).show(); 
	            	}
	            }
	        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}
}
