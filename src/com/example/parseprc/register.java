package com.example.parseprc;

import com.parse.Parse;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
import com.parse.ParseException;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
public class register extends Activity{

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        // Set View to register.xml
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        setContentView(R.layout.register);
	        //requestWindowFeature(Window.FEATURE_NO_TITLE);
	        final EditText FNameField;
	        final EditText LNameField; 
	        final EditText EmailField;
	        final EditText pwdField;
	        final EditText pwdconfField;
	        final ProgressBar spinner;
	        
	        Parse.initialize(this, "xxxxxxxxxx", "xxxxxxxx");
			
	        
	        Button reg = (Button) findViewById(R.id.button1);
	        
	        spinner = (ProgressBar)findViewById(R.id.progressBar1);
	        spinner.setVisibility(View.GONE);
	        TextView loginScreen = (TextView) findViewById(R.id.textView1);
	        TextView tvtitle = (TextView)findViewById(R.id.textView2);
	        tvtitle.setTextColor(Color.parseColor("#4099FF"));
	        loginScreen.setTextColor(Color.parseColor("#4099FF"));
	        
			 tvtitle.setText("Booklog");
			 tvtitle.setTextSize(40.0f);
				String fontPath="fonts/GreatVibes-Regular.ttf";
				Typeface blockFonts = Typeface.createFromAsset(getAssets(),fontPath);
				tvtitle.setTypeface(blockFonts,Typeface.BOLD_ITALIC);
	 
	        //Accepting values from the user
	        FNameField = (EditText) findViewById(R.id.editText1); //First Name
	        
	        LNameField = (EditText) findViewById(R.id.editText2); //Last Name
	        
	        
	        EmailField = (EditText) findViewById(R.id.editText3); //Email
	        
	        
	        pwdField = (EditText) findViewById(R.id.editText4); //Password
	        
	        
	        pwdconfField = (EditText) findViewById(R.id.editText5); //Confirm Password
	        
	        
	        
	        
	        reg.setOnClickListener(new View.OnClickListener() {
	       	 
	            public void onClick(View arg0) {
	                               
	            	
	            	spinner.setVisibility(View.VISIBLE);
	            	String FName = FNameField.getText().toString();
	    	        Log.v("EDit text",FName);
	    	        
	    	        String LName = LNameField.getText().toString();
	    	      	String Email = EmailField.getText().toString();
	    	       	String pwd = pwdField.getText().toString();
	    	       	String pwdconf = pwdconfField.getText().toString();
	    	        
	    	    	View focusView = null;
	    	    	boolean cancel = false;
	    	    	
	    	    	// Check for a valid confirm password.
	    	    	if (TextUtils.isEmpty(pwdconf)) {
	    	    		pwdconfField.setError("Please Enter the password Again");
	    	    		focusView = pwdconfField;
	    	    		cancel = true;
	    	    		} else if (pwd != null && !pwdconf.equals(pwd)) {
	    	    		pwdField.setError("Passwords Do Not Match!");
	    	    		focusView = pwdField;
	    	    		cancel = true;
	    	    		}

	    	    	// Check for a valid password.
	    	    	if (TextUtils.isEmpty(pwd)) {
	    	    	pwdField.setError("Please Enter a Password");
	    	    	focusView = pwdField;
	    	    	cancel = true;
	    	    	} else if (pwd.length() < 6) {
	    	    	pwdField.setError("Password length cannot be less than 6 Characters");
	    	    	focusView = pwdField;
	    	    	cancel = true;
	    	    	}
	    	        
	    	    	// Check for a valid email address.
	    	    	if (TextUtils.isEmpty(Email)) {
	    	    	EmailField.setError("Please Enter your Email Address");
	    	    	focusView = EmailField;
	    	    	cancel = true;
	    	    	} else if (!Email.contains("@")) {
	    	    	EmailField.setError("Please enter a Valid Email Address");
	    	    	focusView = EmailField;
	    	    	cancel = true;
	    	    	}
	    	    	//Check for empty Firstname and LastName
	    	    	if(TextUtils.isEmpty(FName))
	    	    	{
	    	    		FNameField.setError("Please Enter First Name");
	    	    		cancel=true;
	    	    	}
	    	        if(TextUtils.isEmpty(LName))
	    	        {
	    	        	LNameField.setError("Please Enter Last Name");
	    	    		cancel=true;
	    	        }
	    	        
	    	        
	    	    	if (cancel) {
	    	    		// There was an error; don't attempt login and focus the first
	    	    		// form field with an error.
	    	    		focusView.requestFocus();
	    	    		spinner.setVisibility(View.GONE);
	    	    		} else {
	    	    		
	    	    		

	    	        //Code to send request to parse server with the data
	    	        
	    	        ParseUser user = new ParseUser();
	    	        
	    	        //user.setUsername("Hus11");
	    	        //user.setPassword("hi");
	    	        user.setEmail(Email);
	    	        
	    	        user.setUsername(Email);
	    	        user.setPassword(pwd);
	    	        
	    	        user.put("FirstName",FName);
	    	        user.put("LastName",LName);
	    	         
	    	        // other fields can be set just like with ParseObject
	    	        //user.put("phone", "650-253-0000");
	    	         
	    	       //Method to register using Parse
	    	        user.signUpInBackground(new SignUpCallback() {
	    	   		
	    	   		public void done(ParseException e) {
	    	   		if (e == null) {
	    	   		Toast.makeText(getApplicationContext(), 
                        "Registered Successfully", Toast.LENGTH_LONG).show();
	    	   			finish();
	    	   			spinner.setVisibility(View.GONE);
	    	   		} else {
	    	   		Toast.makeText(getApplicationContext(), 
                        "This Email Address already exists", Toast.LENGTH_LONG).show();
	    	   		spinner.setVisibility(View.GONE);
	    	   		//String exc=e.toString();
	    	   		//Log.v("Exceptiom",exc);
	    	   		}
	    	   		}

					
	    	   		});
					
					
	            	//Toast.makeText(getApplicationContext(),FName, Toast.LENGTH_LONG).show();
	            	Log.v("EDit text",FName);
	            	//Toast.makeText(getApplicationContext(), 
	                       // LName, Toast.LENGTH_LONG).show();
	            	//Toast.makeText(getApplicationContext(), 
	                       // "Email", Toast.LENGTH_LONG).show();
	            	//Toast.makeText(getApplicationContext(), 
	                       // pwd, Toast.LENGTH_LONG).show();
	            	//Toast.makeText(getApplicationContext(), 
	                        //pwdconf, Toast.LENGTH_LONG).show();
	            	
	            	
	            }}
	        });
	        
	    	
	        // Listening to Login Screen link
	        loginScreen.setOnClickListener(new View.OnClickListener() {
	 
	            public void onClick(View arg0) {
	                                // Closing registration screen
	                // Switching to Login Screen/closing register screen
	                finish();
	            }
	        });
	        
	        
	    }
	 
	}
