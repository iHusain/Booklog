package com.example.parseprc;


import java.io.ByteArrayOutputStream;




import com.parse.Parse;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseUser;
import android.os.Bundle;
import android.provider.MediaStore.Images.ImageColumns;
import android.app.Activity;
import android.app.AlertDialog;
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
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class UploadBook extends Activity {
	ImageView img_logo;
	protected static final int CAMERA_REQUEST = 0;
	protected static final int GALLERY_PICTURE = 1;
	private Intent pictureActionIntent = null;
	Bitmap bitmap;



	    String selectedImagePath;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_upload_book);
		
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
		
		final EditText ETauthor;
        final EditText ETbookname; 
        final EditText ETversion;
        final EditText ETisbn;
        final EditText ETprice;
        final EditText ETdescription;
        final Spinner SPcondition;
        final Spinner SPcategory;
       
        
        
        //final ProgressBar spinner;
        //initializing Parse
        Parse.initialize(this, "xxxxxxx", "xxxxxxx");
		
		ETauthor = (EditText)findViewById(R.id.ETauthor);
		ETbookname = (EditText)findViewById(R.id.ETbookname);
		ETisbn = (EditText)findViewById(R.id.editText1);
		ETversion = (EditText)findViewById(R.id.ETversion);
		ETprice = (EditText)findViewById(R.id.ETprice);
		ETdescription = (EditText)findViewById(R.id.ETdesc);
		SPcondition = (Spinner) findViewById(R.id.condition);
		SPcategory = (Spinner) findViewById(R.id.category);
		final RadioButton r1 = (RadioButton) findViewById(R.id.radio0);
		//final Button isbn=(Button)findViewById(R.id.button3);
		//final RadioButton r2 = (RadioButton) findViewById(R.id.radio1);
		//RGprice = (RadioGroup) findViewById(R.id.radioGroup1);
		
		//RGprice.setOnCheckedChangeListener(this);
		
		HandleClick hc = new HandleClick();
	    //findViewById(R.id.butQR).setOnClickListener(hc);
	    findViewById(R.id.button3).setOnClickListener(hc);
	        Button upload = (Button) findViewById(R.id.button1);
	        //Button cancel = (Button) findViewById(R.id.button2);
	       
	        //spinner = (ProgressBar)findViewById(R.id.progressBar1);
	        //spinner.setVisibility(View.GONE);
		img_logo= (ImageView) findViewById(R.id.imageView_pic);
		
	    img_logo.setOnClickListener(new OnClickListener() {
	        public void onClick(View v) {
	            startDialog();
	        }
	    });
	    
	   upload.setOnClickListener(new View.OnClickListener() {
	       	 
            public void onClick(View arg0) {
                               
            	
            	//spinner.setVisibility(View.VISIBLE);
            	String bookname = ETbookname.getText().toString();
    	        Log.v("EDit text",bookname);
    	        
    	        String author = ETauthor.getText().toString();
    	      	String version = ETversion.getText().toString();
    	      	String isbn = ETisbn.getText().toString();
    	      	String price = ETprice.getText().toString();
    	       	String desc = ETdescription.getText().toString();
    	       	String condition = String.valueOf(SPcondition.getSelectedItem());
    	       	String category = String.valueOf(SPcategory.getSelectedItem());

    	       	Log.v("condition",condition);
    	       	final ParseUser user = ParseUser.getCurrentUser();
    	       	Log.v("Email",user.getEmail());


    	      
    	    	View focusView = null;
    	    	boolean cancel = false;
    	    	//Verification of values entered by the user
    	    	if(TextUtils.isEmpty(bookname))
    	    	{
    	    		ETbookname.setError("Please Enter Books Name");
    	    		cancel=true;
    	    		focusView=ETbookname;
    	    	}
    	    	if(TextUtils.isEmpty(author))
    	    	{
    	    		ETauthor.setError("Please Enter the Authors Name");
    	    		cancel=true;
    	    		focusView=ETauthor;
    	    	}
    	    	if(TextUtils.isEmpty(desc))
    	    	{
    	    		ETdescription.setError("Please Enter a short Description of the book");
    	    		cancel=true;
    	    		focusView=ETdescription;
    	    	}
    	    	
    	    	if(TextUtils.isEmpty(version))
    	    	{
    	    		ETversion.setError("Please Enter the books Version");
    	    		cancel=true;
    	    		focusView=ETversion;
    	    	}
    	    	if(TextUtils.isEmpty(isbn))
    	    	{
    	    		ETisbn.setError("Please Enter the books ISBN");
    	    		cancel=true;
    	    		focusView=ETisbn;
    	    	}
    	    	
    	    	if(r1.isChecked())
    	    	{
    	    		ETprice.setText("0");
    	    		price="FREE";
    	    	}
    	    	Log.v("price",ETprice.getText().toString());
    	    	if (cancel) {
    	    		// There was an error; don't attempt login and focus the first
    	    		// form field with an error.
    	    		focusView.requestFocus();
    	    		//spinner.setVisibility(View.GONE);
    	    		} else {
    	    		//Creating a new table and adding details in parse DB
    	    			
    	    			ByteArrayOutputStream stream = new ByteArrayOutputStream();
    	                // Compress image to lower quality scale 1 - 100
    	               bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
    	                byte[] image = stream.toByteArray();
    	                
    	             // Create the ParseFile
    	               ParseFile file = new ParseFile("androidbegin.png", image);
    	                // Upload the image into Parse Cloud
    	               file.saveInBackground();
    	 
    	    			
    	    			ParseObject books = new ParseObject("BookNew");
    	    			books.put("username", user.getEmail());
    	    			books.put("book_name", bookname); 
    	    			books.put("author_name",author); 
    	    			books.put("ISBN", isbn); 
    	    			books.put("version", version); 
    	    			books.put("price", price); 
    	    			books.put("description", desc); 
    	    			books.put("condition", condition); 
    	    			books.put("category", category); 
    	    			books.put("ImageFile", file);
    	    			books.saveInBackground();
    	    			Log.v("check","came in else class");
    	    			 Toast.makeText(getBaseContext(), "Book Uploaded Successfully", Toast.LENGTH_SHORT).show();
    		                Intent home = new Intent(getApplicationContext(), Home.class);
    			               startActivity(home);
    	    			
    	    		}
    	    	
            }
	    }); 
	   
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	 //Action Bar options
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
	 //method to upload picture
	 
	 private class HandleClick implements OnClickListener{
		    public void onClick(View arg0) {
		      Intent intent = new Intent("com.google.zxing.client.android.SCAN");
		      switch(arg0.getId()){
		        
		        case R.id.button3:
		          intent.putExtra("SCAN_MODE", "PRODUCT_MODE");
		        break;
		        
		      }
		      startActivityForResult(intent, 0);    //Barcode Scanner to scan for us
		    }
		  }
		  public void onActivityResult1(int requestCode, int resultCode, Intent intent) {
		    if (requestCode == 0) {
		     // EditText tvStatus=(EditText)findViewById(R.id.editText1);
		      EditText tvResult=(EditText)findViewById(R.id.editText1);
		      if (resultCode == RESULT_OK) {
		       // tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
		        tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
		      } else if (resultCode == RESULT_CANCELED) {
		        //tvStatus.setText("Press a button to start a scan.");
		       tvResult.setText("Scan cancelled.");
		      }
		    }
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


	/* Cursor cursor = getContentResolver()
	                        .query(Media.EXTERNAL_CONTENT_URI,
	                                new String[] {
	                                        Media.DATA,
	                                        Media.DATE_ADDED,
	                                        MediaStore.Images.ImageColumns.ORIENTATION },
	                                Media.DATE_ADDED, null, "date_added ASC");
	                if (cursor != null && cursor.moveToFirst()) {
	                    do {
	                        Uri uri = Uri.parse(cursor.getString(cursor
	                                .getColumnIndex(Media.DATA)));
	                        selectedImagePath = uri.toString();
	                    } while (cursor.moveToNext());
	                    cursor.close();
	                }

	                Log.e("path of the image from camera ====> ",
	                        selectedImagePath);

*/
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
