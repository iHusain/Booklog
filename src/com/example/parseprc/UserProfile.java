package com.example.parseprc;



import java.util.ArrayList;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import android.os.Bundle;
import android.provider.MediaStore.Images.ImageColumns;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.TabHost.TabSpec;

public class UserProfile extends TabActivity {
	
	ImageView img_logo;
	protected static final int CAMERA_REQUEST = 0;
	protected static final int GALLERY_PICTURE = 1;
	private Intent pictureActionIntent = null;
	Bitmap bitmap;
	

	
	String selectedImagePath;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_profile);
		
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
		Parse.initialize(this, "xxxxxxxxx", "xxxxxxxx");
		
		
		
		final TextView TVName; 
        final TextView TVEmail;
        
        TVName = (TextView)findViewById(R.id.Name);
		TVEmail = (TextView)findViewById(R.id.Email);
        img_logo = (ImageView)findViewById(R.id.profilepic);
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);
        
        final ParseUser user = ParseUser.getCurrentUser();
        String useremail=user.getEmail();
        
        //ParseObject user = new ParseObject("User");
        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", useremail);
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
                        descriptions = descriptions + ", " + totem.getString("username");
                    }
                } 
                
                
                String FirstName = null;
                for (ParseObject totem : objects) {
                    if (FirstName == null) {
                        FirstName = totem.getString("FirstName");
                        Log.v("First Name",FirstName);
                    } else {
                        FirstName = FirstName + ", " + totem.getString("FirstName");
                    }
                } 
                
                String LastName = null;
                for (ParseObject totem : objects) {
                    if (LastName == null) {
                    	LastName = totem.getString("LastName");
                        Log.v("Last Name",LastName);
                    } else {
                    	LastName = LastName + ", " + totem.getString("LastName");
                    }
                } 
                String Name = FirstName+" "+LastName;
                TVEmail.setText(Name);
                TVName.setText(descriptions);
            	
            } else {
                // Something went wrong.
            }
          }
        });
        
        
        
        
        TabSpec tab1 = tabHost.newTabSpec("First Tab");
        //TabSpec tab2 = tabHost.newTabSpec("Second Tab");
   
        tab1.setIndicator("Books Uploaded");
        tab1.setContent(new Intent(this,Tab1Activity.class));
        //tab2.setIndicator("Tab2");
        //tab2.setContent(new Intent(this,Tab2Activity.class));
        tabHost.addTab(tab1);
        //tabHost.addTab(tab2);
       
        
        img_logo.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	            startDialog();
	        }});
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_profile, menu);
		return true;
	}@Override
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
	private void startDialog() {
	    AlertDialog.Builder myAlertDialog = new AlertDialog.Builder(this);
	    myAlertDialog.setTitle("Upload Pictures Option");
	    myAlertDialog.setMessage("How do you want to set your picture?");

	    myAlertDialog.setPositiveButton("Gallery",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                    pictureActionIntent = new Intent(
	                            Intent.ACTION_GET_CONTENT, null);
	                    pictureActionIntent.setType("image/*");
	                    pictureActionIntent.putExtra("return-data", true);
	                    startActivityForResult(pictureActionIntent,
	                            GALLERY_PICTURE);
	                }
	            });

	    myAlertDialog.setNegativeButton("Camera",
	            new DialogInterface.OnClickListener() {
	                public void onClick(DialogInterface arg0, int arg1) {
	                    pictureActionIntent = new Intent(
	                            android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
	                    startActivityForResult(pictureActionIntent,
	                            CAMERA_REQUEST);

	                }
	            });
	    myAlertDialog.show();
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

	    super.onActivityResult(requestCode, resultCode, data);
	    if (requestCode == GALLERY_PICTURE) {
	        if (resultCode == RESULT_OK) {
	            if (data != null) {
	                // our BitmapDrawable for the thumbnail
	                BitmapDrawable bmpDrawable = null;
	                // try to retrieve the image using the data from the intent
	                Cursor cursor = getContentResolver().query(data.getData(),
	                        null, null, null, null);
	                if (cursor != null) {

	                    cursor.moveToFirst();

	                    int idx = cursor.getColumnIndex(ImageColumns.DATA);
	                    String fileSrc = cursor.getString(idx);
	                    bitmap = BitmapFactory.decodeFile(fileSrc); // load
	                                                                        // preview
	                                                                        // image
	                    bitmap = Bitmap.createScaledBitmap(bitmap,
	                            120, 150, false);
	                    // bmpDrawable = new BitmapDrawable(bitmapPreview);
	                    img_logo.setImageBitmap(bitmap);
	                } else {

	                    bmpDrawable = new BitmapDrawable(getResources(), data
	                            .getData().getPath());
	                    img_logo.setImageDrawable(bmpDrawable);
	                }

	            } else {
	                Toast.makeText(getApplicationContext(), "Cancelled",
	                        Toast.LENGTH_SHORT).show();
	            }
	        } else if (resultCode == RESULT_CANCELED) {
	            Toast.makeText(getApplicationContext(), "Cancelled",
	                    Toast.LENGTH_SHORT).show();
	        }
	    } else if (requestCode == CAMERA_REQUEST) {
	        if (resultCode == RESULT_OK) {
	            if (data.hasExtra("data")) {

	                // retrieve the bitmap from the intent
	                bitmap = (Bitmap) data.getExtras().get("data");


	
	                bitmap = Bitmap.createScaledBitmap(bitmap, 120,
	                        150, false);
	                // update the image view with the bitmap
	                img_logo.setImageBitmap(bitmap);
	            } else if (data.getExtras() == null) {

	                Toast.makeText(getApplicationContext(),
	                        "No extras to retrieve!", Toast.LENGTH_SHORT)
	                        .show();

	                BitmapDrawable thumbnail = new BitmapDrawable(
	                        getResources(), data.getData().getPath());

	                // update the image view with the newly created drawable
	                img_logo.setImageDrawable(thumbnail);

	            }

	        } else if (resultCode == RESULT_CANCELED) {
	            Toast.makeText(getApplicationContext(), "Cancelled",
	                    Toast.LENGTH_SHORT).show();
	        }
	    }
	}
	
}
