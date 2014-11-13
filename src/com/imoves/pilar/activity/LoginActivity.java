package com.imoves.pilar.activity;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


import com.imoves.pilar.util.*;

import com.imoves.pilar.R;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;



public class LoginActivity extends ActionBarActivity{
	
	Button buttonEntrar,buttonNuevo,buttonSkip;
	EditText editTextEmail,editTextContra; 
	conect conect = new conect();
	String email,contrasena,regId;
	public ProgressDialog loading;
	//CI002_GetLogin login;
	TextView textViewOlvido;
	/*private SQLiteDatabase db;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private BDUsuarioDao usuarioDao;*/
	
	 @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_login);
	        
	      /* DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "cifal-db", null);
			db = helper.getWritableDatabase();
			daoMaster = new DaoMaster(db);
			daoSession = daoMaster.newSession();
			usuarioDao = daoSession.getUsuarioDao();*/
			
			//regId = GCMRegistrar.getRegistrationId(LoginActivity.this);
	        
	        buttonEntrar	= (Button) findViewById(R.id.buttonEntrar);
	        buttonNuevo 	= (Button) findViewById(R.id.buttonNuevo);
	        //buttonSkip		= (Button) findViewById(R.id.buttonSkip);
	        
	        //textViewOlvido 	= (TextView) findViewById(R.id.textViewOlvido);
	        editTextEmail 	= (EditText)findViewById(R.id.editTextEmail);
	        editTextContra 	= (EditText)findViewById(R.id.editTextContra);
	        
	        /*buttonSkip.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) { 
					Intent i = new Intent(LoginActivity.this, MainActivity.class);
					i.putExtra("variable", "0");
					startActivity(i);				
					finish();
				}
			});*/
	        
	        
	        
	        buttonEntrar.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					
					//if(!conect.verificaConexion(LoginActivity.this)){
					//	Toast.makeText(LoginActivity.this, "No connection!", Toast.LENGTH_LONG).show();
					//}else{
						/*email = editTextEmail.getText().toString();
						contrasena = editTextContra.getText().toString();
						if(email.equals("")){
							Toast.makeText(LoginActivity.this, "Enter the email", Toast.LENGTH_LONG).show();
						//}else if(!email.trim().matches("[a-zA-Z0-9._-]+@[a-zA-Z0-9._-]+\\.+[a-z]+")){
						//	Toast.makeText(LoginActivity.this, "Invalid Email", Toast.LENGTH_LONG).show();
						}else if(contrasena.equals("")){
							Toast.makeText(LoginActivity.this, "Enter the password", Toast.LENGTH_LONG).show();
						}else if(contrasena.length()<4){
							Toast.makeText(LoginActivity.this, "The password must be at least 8 characters", Toast.LENGTH_LONG).show();
						}else{
							//new GetDataTask(email,contrasena).execute();
							
						}*/
				//	}
					
					Intent n = new Intent(LoginActivity.this,MainActivity.class);
					startActivity(n);
					finish();		
				}
			});
	        buttonNuevo.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//Intent n = new Intent(LoginActivity.this,RegistroActivity.class);
					//startActivity(n);
					//finish();
				}
			});
	        
	        /*textViewOlvido.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent n = new Intent(LoginActivity.this,RecuperarContrasena1.class);
					startActivity(n);
					finish();
				}
			});*/
	 }
	 
	/* private class GetDataTask extends AsyncTask<Void, Void, Void> {

			int resultado;
			String email,contrasena,contra;		
			
			public GetDataTask(String email, String contrasena) {
				this.email = email;
				this.contrasena = contrasena;
				//this.regId = regId;
			}

			@Override
			protected void onPreExecute() {
				try {
					loading = new ProgressDialog(LoginActivity.this);
					loading.setMessage("Loading");
					loading.setCancelable(false);
					loading.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			@Override
			protected Void doInBackground(Void... arg0) {
				// TODO Auto-generated method stub
				
				try {
					contra = SHA1(contrasena);
					login = new CI002_GetLogin(email, contra);
					resultado = login.result;
									
				} catch (NoSuchAlgorithmException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
					
				return null;
			}
			
			@Override
			protected void onPostExecute(Void result) {

					if(resultado==0){
						usuarioDao.deleteAll();
						BDUsuario miembro = new BDUsuario(
								Long.parseLong(login.idusuarioAplicacion), 
								login.nombre+" "+login.apellido, 
								login.fechaNacimiento,
								login.genero,
								login.nombreNacionalidad,
								login.correoE,
								"");
						usuarioDao.insert(miembro);
						Intent n = new Intent(LoginActivity.this,MainActivity.class);
						n.putExtra("variable", "1");
						startActivity(n);
						finish();		
					 
					if(resultado==1){			
						Toast.makeText(LoginActivity.this, "No DATA available", Toast.LENGTH_LONG).show();
					//Incorrect DATA was received
						
					}else if(resultado==2){			
					Toast.makeText(LoginActivity.this, "An error occured, please try again later", Toast.LENGTH_LONG).show();					
					}else if(resultado==3){			
						Toast.makeText(LoginActivity.this, "Incorrect DATA was received", Toast.LENGTH_LONG).show();
					}else if(resultado==15){			
						Toast.makeText(LoginActivity.this, "No connection!", Toast.LENGTH_LONG).show();
					}
					loading.dismiss();
					}
			}
		}
		@Override
		protected void onDestroy() {
		    super.onDestroy();
		    db.close();
		}
		
		private static String convertToHex(byte[] data) {
			StringBuilder buf = new StringBuilder();
			for (byte b : data) {
				int halfbyte = (b >>> 4) & 0x0F;
				int two_halfs = 0;
				do {
					buf.append((0 <= halfbyte) && (halfbyte <= 9) ? (char) ('0' + halfbyte) : (char) ('a' + (halfbyte - 10)));
					halfbyte = b & 0x0F;
				} while (two_halfs++ < 1);
			}
			return buf.toString();
		}

		public static String SHA1(String text) throws NoSuchAlgorithmException, UnsupportedEncodingException {
			MessageDigest md = MessageDigest.getInstance("SHA-1");

			md.update(text.getBytes("iso-8859-1"), 0, text.length());
			byte[] sha1hash = md.digest();
			Log.i("data---", "" + sha1hash);
			return convertToHex(sha1hash);
		}*/
}
