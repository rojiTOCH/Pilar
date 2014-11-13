package com.imoves.pilar.activity;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;

import com.imoves.pilar.R;




public class SplashScreenActivity extends Activity {
	
	private long splashDelay = 3000; //3 segundos

    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splashscreen);

 
        	TimerTask task = new TimerTask() {
    			@Override 
    			public void run() {
    				 
    				Intent mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
    				startActivity(mainIntent);
    				finish();//Destruimos esta activity para prevenit que el usuario retorne aqui presionando el boton Atras.
    			}
    		};
    		Timer timer = new Timer();
    		timer.schedule(task, splashDelay);//Pasado los 6 segundos dispara la tarea
       
	}
	
	
}
