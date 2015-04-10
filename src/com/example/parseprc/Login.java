package com.example.parseprc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.LogInCallback;

import org.json.JSONObject;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity {
	private TextView tv;
	private TextView tvforgotpass,tvtitle;
	private Button login_button;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//ParseUser CurrentUser = ParseUser.getCurrentUser();
		
		    // show the signup or login screen
		
		setContentView(R.layout.login);
		
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		//Authorizing the APP with PARSE
		Parse.initialize(this, "gvDvrHAlecvnVOF8vXDmU18WyHPTTOvGdU3k1o0e", "uD10O3dhh117vAC8lXdfbylQOQMsG0WL9n0s0PhF");
		
		
		
		final EditText EmailField = (EditText) findViewById(R.id.editText1); //Email
		final EditText PasswordField = (EditText) findViewById(R.id.editText2); //Password
		
		EmailField.setText("");
		PasswordField.setText("");
		
		 tv = (TextView)findViewById(R.id.textView1);
		 tvforgotpass = (TextView)findViewById(R.id.textView2);
		 tvtitle = (TextView)findViewById(R.id.textView3);
		 tv.setTextColor(Color.parseColor("#4099FF"));
		 tvforgotpass.setTextColor(Color.parseColor("#4099FF"));
		 login_button = (Button)findViewById(R.id.button1);
		 
		 tvtitle.setTextColor(Color.parseColor("#4099FF"));
		 tvtitle.setText("Booklog");
		 tvtitle.setTextSize(40.0f);
			String fontPath="fonts/GreatVibes-Regular.ttf";
			Typeface blockFonts = Typeface.createFromAsset(getAssets(),fontPath);
			tvtitle.setTypeface(blockFonts,Typeface.BOLD_ITALIC);
		 
		 
		 login_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//ParseObject testObject = new ParseObject("TestObject");
				//testObject.put("HUSAIN", "MANDSAURWALA");
				//testObject.saveInBackground();
				String email=EmailField.getText().toString();
				String pwd=PasswordField.getText().toString();
				//Log.v("Email",email);
				//Log.v("Passwd",pwd);
				
				View focusView = null;
    	    	boolean cancel = false;
    	    	
    	    	if(TextUtils.isEmpty(email))
    	    	{
    	    		EmailField.setError("Please Enter Email Address");
    	    		focusView = EmailField;
    	    		cancel = true;
    	    	}
				
    	    	if(TextUtils.isEmpty(pwd))
    	    	{
    	    		PasswordField.setError("Please Enter Password");
    	    		focusView = PasswordField;
    	    		cancel = true;
    	    	}
    	    	
    	    	if (cancel) {
    	    		// There was an error; don't attempt login and focus the first
    	    		// form field with an error.
    	    		focusView.requestFocus();
    	    		} else {
    	    		
    	    	
				ParseUser.logInInBackground(email, pwd, new LogInCallback() {
					   public void done(ParseUser user, ParseException e) {
					     if (e == null && user != null) {
					       //loginSuccessful();
					    	 Toast.makeText(getApplicationContext(), 
				                       "Success", Toast.LENGTH_LONG).show();
					    	 Intent home = new Intent(getApplicationContext(), Home.class);
				               startActivity(home);
					     } else if (user == null) {
					       //usernameOrPasswordIsInvalid();
					    	 Toast.makeText(getApplicationContext(), 
				                       "Username/Password Invalid!", Toast.LENGTH_LONG).show();
					    	 //String exce = e.toString();
					    	 //Log.v("Exception",exce);
					     } else {
					       //somethingWentWrong();
					    	 String exce = e.toString();
					    	 Log.v("Exception",exce);
					     }
					   }
					 });
    	    		}
			}
			});
		 
		 
		 tvforgotpass.setOnClickListener(new View.OnClickListener() {
			   @Override
			   public void onClick(View v) {
			      // TODO Auto-generated method stub
				   Intent fp = new Intent(getApplicationContext(), Forgotpassword.class);
	               startActivity(fp);
			   }
			});
		 
		 
	tv.setOnClickListener(new View.OnClickListener() {
		   @Override
		   public void onClick(View v) {
		      // TODO Auto-generated method stub
			   Intent i = new Intent(getApplicationContext(), register.class);
               startActivity(i);
		   }
		});}
	@Override
public boolean onCreateOptionsMenu(Menu menu) {
	// Inflate the menu; this adds items to the action bar if it is present.
	getMenuInflater().inflate(R.menu.main, menu);
	return true;
	
	}}