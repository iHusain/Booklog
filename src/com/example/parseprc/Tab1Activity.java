package com.example.parseprc;

import java.util.ArrayList;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ProgressDialog;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import android.widget.ListView;


public class Tab1Activity  extends Activity
{
	ListView listview;
	List<ParseObject> ob;
	ProgressDialog mProgressDialog;
	ListViewAdapter adapter;
	private List<WorldPopulation> worldpopulationlist = null;
	
        @Override
        public void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home);
           
            /*TextView  tv=new TextView(this);
            tv.setTextSize(25);
            tv.setGravity(Gravity.CENTER_VERTICAL);
            tv.setText("This Is Tab1 Activity");
            
            setContentView(tv);*/
            new RemoteDataTask().execute();
        }
        
        
        private class RemoteDataTask extends AsyncTask<Void, Void, Void> {
    		@Override
    		protected void onPreExecute() {
    			super.onPreExecute();
    			// Create a progressdialog
    			mProgressDialog = new ProgressDialog(Tab1Activity.this);
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
    				query.whereEqualTo("username","husainmandar@gmail.com");
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
    			adapter = new ListViewAdapter(Tab1Activity.this,
    					worldpopulationlist);
    			// Binds the Adapter to the ListView
    			listview.setAdapter(adapter);
    			// Close the progressdialog
    			mProgressDialog.dismiss();
    		}
    	}
}