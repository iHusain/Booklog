package com.example.parseprc;



import java.util.ArrayList;
import java.util.List;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;

import android.util.Log;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Home extends Activity {
	
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapter adapter;
	private List<WorldPopulation> worldpopulationlist = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
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
		//ActionBar actionBar = getActionBar();
		//actionBar.setSplitBackgroundDrawable(new ColorDrawable(Color.RED));
		new RemoteDataTask().execute();
	}

	@Override
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
	 private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				// Create a progressdialog
				mProgressDialog = new ProgressDialog(Home.this);
				// Set progressdialog title
				mProgressDialog.setTitle("");
				// Set progressdialog message
				mProgressDialog.setMessage("Loading...");
				mProgressDialog.setIndeterminate(false);
				// Show progressdialog
				mProgressDialog.show();
			}

			@Override
			protected Void doInBackground(Void... params) {
				// Create the array
				worldpopulationlist = new ArrayList<WorldPopulation>();
				try {
					// Locate the class table named "Country" in Parse.com
					ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
							"BookNew");
					// Locate the column named "ranknum" in Parse.com and order list
					// by ascending
					query.orderByDescending("createdAt");
					ob = query.find();
					for (ParseObject country : ob) {
						// Locate images in flag column
						ParseFile image = (ParseFile) country.get("ImageFile");
						
						WorldPopulation map = new WorldPopulation();
						map.setRank((String) country.get("book_name"));
						map.setCountry((String) country.get("author_name"));
						map.setID((String) country.get("objectId"));
						//map.setPopulation((String) country.get("population"));
						map.setFlag(image.getUrl());
						worldpopulationlist.add(map);
					}
				} catch (ParseException e) {
					Log.e("Error", e.getMessage());
					e.printStackTrace();
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				// Locate the listview in listview_main.xml
				listview = (ListView) findViewById(R.id.listview);
				// Pass the results into ListViewAdapter.java
				adapter = new ListViewAdapter(Home.this,
						worldpopulationlist);
				// Binds the Adapter to the ListView
				listview.setAdapter(adapter);
				// Close the progressdialog
				mProgressDialog.dismiss();
			}
		}
	}

