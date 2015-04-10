package com.example.parseprc;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
public class BarcodeScan extends Activity {
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_upload_book);
	    HandleClick hc = new HandleClick();
	    //findViewById(R.id.butQR).setOnClickListener(hc);
	    findViewById(R.id.button3).setOnClickListener(hc);
	    //findViewById(R.id.butOther).setOnClickListener(hc);
	  }
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
	  public void onActivityResult(int requestCode, int resultCode, Intent intent) {
	    if (requestCode == 0) {
	     // TextView tvStatus=(TextView)findViewById(R.id.tvStatus);
	      EditText tvResult=(EditText)findViewById(R.id.editText1);
	      if (resultCode == RESULT_OK) {
	        //tvStatus.setText(intent.getStringExtra("SCAN_RESULT_FORMAT"));
	        tvResult.setText(intent.getStringExtra("SCAN_RESULT"));
	      } else if (resultCode == RESULT_CANCELED) {
	        //tvStatus.setText("Press a button to start a scan.");
	        tvResult.setText("Scan cancelled.");
	      }
	    }
	  }
	}