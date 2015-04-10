package com.example.parseprc;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Forgotpassword extends Activity {

	
	private Button send;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_forgotpassword);
		Parse.initialize(this, "xxxxxxxxx", "xxxxxxxxxx");
		
		final EditText emailfield = (EditText) findViewById(R.id.editText1);
		
		send=(Button)findViewById(R.id.button1);
		
		send.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
		String email=emailfield.getText().toString();
	Log.v("Email",email);
		ParseUser.requestPasswordResetInBackground(email,
                new RequestPasswordResetCallback() {
			public void done(ParseException e) {
					if (e == null) {
							// An email was successfully sent with reset instructions.
						finish();
						} else {
								// Something went wrong. Look at the ParseException to see what's up.
							Log.v("Error",e.toString());
							emailfield.setError("No user record found for this Email Address");
			    	    	
							}
						}
					});
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.forgotpassword, menu);
		return true;
	}

}
