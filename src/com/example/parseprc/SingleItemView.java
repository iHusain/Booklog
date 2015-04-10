package com.example.parseprc;

import java.util.List;

import com.parse.FindCallback;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.parse.ParseException;

public class SingleItemView extends Activity {
	// Declare Variables
	String rank;
	String country;
	String id;
	String flag;
	String position;
	ImageLoader imageLoader = new ImageLoader(this);

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Get the view from singleitemview.xml
		setContentView(R.layout.singleitemview);
		
		
		int titleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
		TextView Tv = (TextView)findViewById(titleId);
		Tv.setTextColor(Color.parseColor("#F0FFFF"));
		Tv.setText("Booklog");
		Tv.setTextSize(30.0f);
		String fontPath="fonts/GreatVibes-Regular.ttf";
		Typeface blockFonts = Typeface.createFromAsset(getAssets(),fontPath);
		Tv.setTypeface(blockFonts,Typeface.BOLD_ITALIC);
		
		//Tv.setGravity(Gravity.CENTER);
		android.app.ActionBar ab = getActionBar(); 
		ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#4099FF")));
		//ab.setDisplayShowTitleEnabled(false); 
		ab.setDisplayShowHomeEnabled(false);

		Intent i = getIntent();
		// Get the result of rank
		rank = i.getStringExtra("rank");
		// Get the result of country
		country = i.getStringExtra("country");
	
		id = i.getStringExtra("ID");
		// Get the result of flag
		flag = i.getStringExtra("flag");

		
		
		// Locate the TextViews in singleitemview.xml
		final TextView txtrank = (TextView) findViewById(R.id.rank);
		final TextView txtcountry = (TextView) findViewById(R.id.country);
		final TextView txtemail = (TextView) findViewById(R.id.population);
		
		final TextView txtisbn = (TextView) findViewById(R.id.textView1);
		final TextView txtversion = (TextView) findViewById(R.id.textView2);
		final TextView txtcategory = (TextView) findViewById(R.id.textView3);
		final TextView txtcondition = (TextView) findViewById(R.id.textView4);
		final TextView txtprice = (TextView) findViewById(R.id.textView5);
		final TextView txtdescription = (TextView) findViewById(R.id.textView6);
		
		
		
		final TextView txtrank1 = (TextView) findViewById(R.id.ranklabel);
		final TextView txtcountry1 = (TextView) findViewById(R.id.countrylabel);
		final TextView txtemail1 = (TextView) findViewById(R.id.populationlabel);
		
		final TextView txtisbn1 = (TextView) findViewById(R.id.Isbn);
		final TextView txtversion1 = (TextView) findViewById(R.id.Version);
		final TextView txtcategory1 = (TextView) findViewById(R.id.Category);
		final TextView txtcondition1 = (TextView) findViewById(R.id.Condition);
		final TextView txtprice1 = (TextView) findViewById(R.id.Price);
		final TextView txtdescription1 = (TextView) findViewById(R.id.Desc);
		
		
		String fontPath1="fonts/Aspergit.otf";
		final Typeface blockFonts1 = Typeface.createFromAsset(getAssets(),fontPath1);
		
		//String email = gameScore.getString("username");
		
		
		
		ParseQuery<ParseUser> query = ParseQuery.getQuery("BookNew");
        query.whereEqualTo("book_name", rank);
        query.findInBackground(new FindCallback<ParseUser>() {
          public void done(List<ParseUser> objects, ParseException e) {
            if (e == null) {
                // The query was successful.
            	
                String descriptions = null;
                for (ParseObject totem : objects) {
                    if (descriptions == null) {
                        descriptions = totem.getString("username");
                        Log.v("UserEmailId",descriptions);
                    } else {
                    	descriptions = totem.getString("username");
                    }
                } 
                
                
                String isbn = null;
                for (ParseObject totem : objects) {
                    if (isbn == null) {
                    	isbn = totem.getString("ISBN");
                        Log.v("ISBN",isbn);
                    } else {
                    	isbn = totem.getString("ISBN");
                    }
                } 
                
                String version = null;
                for (ParseObject totem : objects) {
                    if (version == null) {
                    	version = totem.getString("version");
                        Log.v("Version",version);
                    } else {
                    	version = totem.getString("version");
                    }
                } 
                
                String categ=null;
                for (ParseObject totem : objects) {
                    if (categ == null) {
                    	categ = totem.getString("category");
                        Log.v("Category",categ);
                    } else {
                    	categ = totem.getString("category");
                    }
                } 
                
                String condition = null;
                for (ParseObject totem : objects) {
                    if (condition == null) {
                    	condition = totem.getString("condition");
                        Log.v("Condition",condition);
                    } else {
                    	condition = totem.getString("condition");
                    }
                } 
                
                String price = null;
                for (ParseObject totem : objects) {
                    if (price == null) {
                    	price = totem.getString("price");
                        Log.v("Price",price);
                    } else {
                    	price = totem.getString("price");
                    }
                } 
                
                String Desc = null;
                for (ParseObject totem : objects) {
                    if (Desc == null) {
                    	Desc = totem.getString("description");
                        Log.v("Last Name",Desc);
                    } else {
                    	Desc = totem.getString("description");
                    }
                } 
                
                txtemail.setText(descriptions);
                txtisbn.setText(isbn);
                txtversion.setText(version);
                txtcategory.setText(categ);
                txtcondition.setText(condition);
                txtprice.setText(price);
                txtdescription.setText(Desc);
                
                
        		
        		txtrank.setTextSize(15.0f);
        		txtrank.setTypeface(blockFonts1,Typeface.BOLD);
        		txtrank1.setTextSize(15.0f);
        		txtrank1.setTypeface(blockFonts1,Typeface.BOLD);
        		txtcountry.setTextSize(15.0f);
        		txtcountry.setTypeface(blockFonts1,Typeface.BOLD);
        		txtemail.setTextSize(15.0f);
        		txtemail.setTypeface(blockFonts1,Typeface.BOLD);
        		txtisbn.setTextSize(15.0f);
        		txtisbn.setTypeface(blockFonts1,Typeface.BOLD);
        		txtversion.setTextSize(15.0f);
        		txtversion.setTypeface(blockFonts1,Typeface.BOLD);
        		txtcategory.setTextSize(15.0f);
        		txtcategory.setTypeface(blockFonts1,Typeface.BOLD);
        		txtcondition.setTextSize(15.0f);
        		txtcondition.setTypeface(blockFonts1,Typeface.BOLD);
        		txtprice.setTextSize(15.0f);
        		txtprice.setTypeface(blockFonts1,Typeface.BOLD);
        		txtdescription.setTextSize(15.0f);
        		txtdescription.setTypeface(blockFonts1,Typeface.BOLD);
        		txtcountry1.setTextSize(15.0f);
        		txtcountry1.setTypeface(blockFonts1,Typeface.BOLD);
        		txtemail1.setTextSize(15.0f);
        		txtemail1.setTypeface(blockFonts1,Typeface.BOLD);
        		txtisbn1.setTextSize(15.0f);
        		txtisbn1.setTypeface(blockFonts1,Typeface.BOLD);
        		txtversion1.setTextSize(15.0f);
        		txtversion1.setTypeface(blockFonts1,Typeface.BOLD);
        		txtcategory1.setTextSize(15.0f);
        		txtcategory1.setTypeface(blockFonts1,Typeface.BOLD);
        		txtcondition1.setTextSize(15.0f);
        		txtcondition1.setTypeface(blockFonts1,Typeface.BOLD);
        		txtprice1.setTextSize(15.0f);
        		txtprice1.setTypeface(blockFonts1,Typeface.BOLD);
        		txtdescription1.setTextSize(15.0f);
        		txtdescription1.setTypeface(blockFonts1,Typeface.BOLD);
                
                
                //TVName.setText(descriptions);
            	
            } else {
                // Something went wrong.
            }
          }
        });

		// Locate the ImageView in singleitemview.xml
		ImageView imgflag = (ImageView) findViewById(R.id.flag);

		// Set results to the TextViews
		txtrank.setText(rank);
		txtcountry.setText(country);
		//txtpopulation.setText(email);
		

		// Capture position and set results to the ImageView
		// Passes flag images URL into ImageLoader.class
		imageLoader.DisplayImage(flag, imgflag);
		
		
		
	}
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	 
	        super.onOptionsItemSelected(item);
	 
	        switch(item.getItemId()){
	            case R.id.home:
	                Toast.makeText(getBaseContext(), "You selected Home", Toast.LENGTH_SHORT).show();
	                Intent home = new Intent(getApplicationContext(), Home.class);
		               startActivity(home);
	                break;
	 
	            case R.id.search:
	                Toast.makeText(getBaseContext(), "You selected Search", Toast.LENGTH_SHORT).show();
	                Intent search = new Intent(getApplicationContext(), Search.class);
		               startActivity(search);
	                break;
	 
	            case R.id.user:
	                Toast.makeText(getBaseContext(), "You selected Profile", Toast.LENGTH_SHORT).show();
	                Intent userprofile = new Intent(getApplicationContext(), UserProfile.class);
		               startActivity(userprofile);
	                break;
	 
	           
	            case R.id.sell:
	                Toast.makeText(getBaseContext(), "You selected Sell", Toast.LENGTH_SHORT).show();
	                Intent sellbook = new Intent(getApplicationContext(), UploadBook.class);
		               startActivity(sellbook);
		               	break;
	            }
	        return true;
	    }
}