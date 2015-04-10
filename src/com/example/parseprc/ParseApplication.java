package com.example.parseprc;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;
import android.app.Application;

public class ParseApplication extends Application {

	@Override
	public void onCreate() {
		super.onCreate();

		// Add your initialization code here
		Parse.initialize(this, "gvDvrHAlecvnVOF8vXDmU18WyHPTTOvGdU3k1o0e", "uD10O3dhh117vAC8lXdfbylQOQMsG0WL9n0s0PhF");

		ParseUser.enableAutomaticUser();
		ParseACL defaultACL = new ParseACL();

		// If you would like all objects to be private by default, remove this
		// line.
		defaultACL.setPublicReadAccess(true);

		ParseACL.setDefaultACL(defaultACL, true);
	}

}
