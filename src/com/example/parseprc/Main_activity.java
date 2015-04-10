package com.example.parseprc;

import com.example.parseprc.util.SystemUiHider;
import com.parse.Parse;
import com.parse.ParseUser;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;

public class Main_activity extends Activity {
	 public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        requestWindowFeature(Window.FEATURE_NO_TITLE);
	        
	        Parse.initialize(this, "xxxxxxx", "xxxxxxxxx");
	        ParseUser currentUser = ParseUser.getCurrentUser();
	        if (currentUser != null) {
	          // do stuff with the user
	        	//setContentView(R.layout.activity_home);
	        	Intent home = new Intent(getApplicationContext(), Home.class);
	               startActivity(home);
	        } else {
	          // show the signup or login screen
	        	//setContentView(R.layout.login);
	        	Intent login = new Intent(getApplicationContext(), Login.class);
	               startActivity(login);
	        }
	        
	 }
}
