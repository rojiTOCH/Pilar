package com.imoves.pilar.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.imoves.pilar.R;
import com.imoves.pilar.adapter.CategoriaAdapter;
import com.imoves.pilar.fragment.AlianzaFragment;
import com.imoves.pilar.fragment.DirectorioFragment;
import com.imoves.pilar.fragment.EventoFragment;
import com.imoves.pilar.fragment.HomeFragment;
 

public class MainActivity extends ActionBarActivity {
	
	int mPosition = -1;	
	String mTitle = "";
	String idusuarioapp;
	// Array of strings storing country names
    String[] mCountries ;
	private SQLiteDatabase db;
	CategoriaAdapter adapter;
    
    // Array of integers points to images stored in /res/drawable-ldpi/
    int[] flagsactivo = new int[]{ 
    			R.drawable.home,
    			R.drawable.img_ic_directorio,
    			R.drawable.img_ic_evento,
    			R.drawable.img_ic_alianza,
    			R.drawable.img_ic_logout
    };
    
    /*	int[] flagsinactivo = new int[]{
    		R.drawable.home,
			R.drawable.azul,
			R.drawable.rojo,
			R.drawable.naranja,
			R.drawable.verde,
			R.drawable.amarillo,
			R.drawable.transparente,
			R.drawable.transparente,
			R.drawable.plus
    };*/

    // Array of strings to initial counts
    String[] mCount = new String[]{
        "", "", "", "", "","","", "", "","" }; 
    
    String[] mCount2 = new String[]{
            "", "", "", "", "","","", "", "" }; 
	
	private DrawerLayout mDrawerLayout;	
	private ListView mDrawerList;	
	private ActionBarDrawerToggle mDrawerToggle;	
	private LinearLayout mDrawer ;	
	private List<HashMap<String,String>> mList ;	
	private SimpleAdapter mAdapter;	
	final private String COUNTRY = "country";	
	final private String FLAG = "flag";	
	final private String COUNT = "count"; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);


		
		// Getting an array of country names
		
		
		// Title of the activity
		mTitle = (String)getTitle();
		
		// Getting a reference to the drawer listview
		mDrawerList = (ListView) findViewById(R.id.drawer_list);
		// Getting a reference to the sidebar drawer ( Title + ListView )
		mDrawer = ( LinearLayout) findViewById(R.id.drawer);
		
		// Each row in the list stores country name, count and flag
        mList = new ArrayList<HashMap<String,String>>();
        
        
        	mCountries = getResources().getStringArray(R.array.drawer);
            for(int i=0;i<flagsactivo.length;i++){
                HashMap<String, String> hm = new HashMap<String,String>();
                hm.put(COUNTRY, mCountries[i]);
                hm.put(COUNT, mCount[i]);
                hm.put(FLAG, Integer.toString(flagsactivo[i]));
                mList.add(hm);
            }
		
        

        // Keys used in Hashmap
        String[] from = { FLAG,COUNTRY,COUNT };

        // Ids of views in listview_layout
        int[] to = { R.id.flag , R.id.country };

        // Instantiating an adapter to store each items
        // R.layout.drawer_layout defines the layout of each item
       // mAdapter = new SimpleAdapter(this, mList, R.layout.drawer_layout, from, to);
        adapter = new CategoriaAdapter(this, mCountries,flagsactivo);
        // Getting reference to DrawerLayout
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);        
        
       
 
        // Creating a ToggleButton for NavigationDrawer with drawer event listener
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer , R.string.drawer_open,R.string.drawer_close){
        	
        	 /** Called when drawer is closed */
            public void onDrawerClosed(View view) {               
            	highlightSelectedCountry();            		
                supportInvalidateOptionsMenu();       
            }

            /** Called when a drawer is opened */
            public void onDrawerOpened(View drawerView) {            	
                getSupportActionBar().setTitle("Select the option");            	
            	supportInvalidateOptionsMenu();                
            }
        };
        
        // Setting event listener for the drawer
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        showFragment(0);
        // ItemClick event handler for the drawer items
        mDrawerList.setOnItemClickListener(new OnItemClickListener() {

			@SuppressLint("NewApi")
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				
				// Increment hit count of the drawer list item
				incrementHitCount(position);			
				 
		 			
				 showFragment(position);
			 
				
				// Closing the drawer
				mDrawerLayout.closeDrawer(mDrawer);		
			}
		});
        
        
        // Enabling Up navigation
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);     
        
        getSupportActionBar().setDisplayShowHomeEnabled(true);        

        // Setting the adapter to the listView
        mDrawerList.setAdapter(adapter);   
        
	}
	
	
	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		mDrawerToggle.syncState();
		
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (mDrawerToggle.onOptionsItemSelected(item)) {
	          return true;
	    }
		return super.onOptionsItemSelected(item);
	}	
			 
	
	public void incrementHitCount(int position){
		HashMap<String, String> item = mList.get(position);
		String count = item.get(COUNT);
		item.remove(COUNT);
		if(count.equals("")){
			count = "  1  ";
		}else{
			int cnt = Integer.parseInt(count.trim());
			cnt ++;
			count = "  " + cnt + "  ";
		}				
		item.put(COUNT, count);				
		adapter.notifyDataSetChanged();
	}
	
	@SuppressLint("NewApi")
	public void showFragment(int position){
		
		//Currently selected country
        mTitle = mCountries[position];	
 
        	if (position==0) {
            	Fragment fragment = new HomeFragment();  
    	        FragmentManager fragmentManager = getSupportFragmentManager();
    	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    		} else if (position==1){
    			Fragment fragment = new DirectorioFragment();  
    	        FragmentManager fragmentManager = getSupportFragmentManager();
    	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    		}else if (position==2){
    			Fragment fragment = new EventoFragment();  
    	        FragmentManager fragmentManager = getSupportFragmentManager();
    	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    		}else if (position==3){
    			Fragment fragment = new AlianzaFragment();  
    	        FragmentManager fragmentManager = getSupportFragmentManager();
    	        fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();
    		}else if (position==4){
    			Intent c = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(c);
				finish();
    		}
       
        
        
	}
	
	// Highlight the selected country : 0 to 4
	public void highlightSelectedCountry(){
		int selectedItem = mDrawerList.getCheckedItemPosition();
    	
    	if(selectedItem > 8)
    		mDrawerList.setItemChecked(mPosition, true);
    	else
    		mPosition = selectedItem;
    	
    	if(mPosition!=-1)
    		getSupportActionBar().setTitle(mCountries[mPosition]);
	}	
	
	/*public void Logout()
	{
		 AlertDialog.Builder builder;
		 final AlertDialog alertDialog;
		 LayoutInflater inflater = (LayoutInflater) this.getSystemService(this.LAYOUT_INFLATER_SERVICE);
		 // Nos guardamos una referencia a nuestro layout
		 View layout = inflater.inflate(R.layout.fragment_alertdialog, (ViewGroup) this.findViewById(R.id.layout_root1));

		 builder = new AlertDialog.Builder(this);
		 // Asignamos la vista del AlertDialog a nuestro propio layout
		 builder.setView(layout);

		 alertDialog = builder.create();
		 final TextView titu 	= (TextView) layout.findViewById(R.id.titulonoti); 

		 Button aceptarBtn 	= (Button) layout.findViewById(R.id.btnaceptar);
		 Button cancelarBtn = (Button) layout.findViewById(R.id.btncancelar);

		 titu.setText("Are you sure to want to logout?");
		 aceptarBtn.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) 
			 { 
				 
				 		DAO SETTINGS
				 
				DevOpenHelper helper = new DaoMaster.DevOpenHelper(getApplicationContext(),"cifal-db", null);
				db = helper.getWritableDatabase();
				daoMaster = new DaoMaster(db);
				daoSession = daoMaster.newSession();
				usuariodao = daoSession.getUsuarioDao(); 
				usuariodao.deleteAll();
				
				Intent in = new Intent(MainActivity.this, LoginActivity.class);
				startActivity(in);
				finish();
			 }
		 });
		 cancelarBtn.setOnClickListener(new OnClickListener() {
			 @Override
			 public void onClick(View v) {
				 alertDialog.dismiss();
			 }
		 });
		 alertDialog.show();
	} */
	 
}