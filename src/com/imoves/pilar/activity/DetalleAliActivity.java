package com.imoves.pilar.activity;

import com.imoves.pilar.R;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.MenuItem;

public class DetalleAliActivity extends ActionBarActivity{
	

	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_detalle1);
	        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			getSupportActionBar().setHomeButtonEnabled(true);
	 }


	 @Override
		public boolean onOptionsItemSelected(MenuItem item) {

			switch (item.getItemId()) {

			case android.R.id.home:
				
				finish();
				return true;
				
			default:
				return super.onOptionsItemSelected(item);
			}
		}
		
		@Override
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				
				finish();
				return true;
			}
			return super.onKeyDown(keyCode, event);
		}
}
