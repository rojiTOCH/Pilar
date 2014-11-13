package com.imoves.pilar.fragment;

import com.imoves.pilar.R;

import uk.co.senab.actionbarpulltorefresh.library.ActionBarPullToRefresh;
import uk.co.senab.actionbarpulltorefresh.extras.actionbarcompat.PullToRefreshLayout;
import uk.co.senab.actionbarpulltorefresh.library.listeners.OnRefreshListener;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SearchView.OnQueryTextListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RelativeLayout; 
import android.widget.Toast;
import android.support.v7.widget.SearchView;



@SuppressLint("NewApi")
public class HomeFragment extends Fragment implements OnRefreshListener {
	private PullToRefreshLayout mPullToRefreshLayout;
	//ListaCategoriasAdapter categoriasadapter;
	ProgressDialog cargar;
	ListView listado; 
	private boolean searchCheck;
	private SQLiteDatabase db;
	/*private DaoMaster daoMaster; 
	private DaoSession daoSession;
	private BDUsuarioDao usuarioDao;
	private BDCursosUsuarioDao cursosusuariodao;*/
	String idusuario;
	Cursor cursorusuario;
	//CI006_GetCursosUsuario getcursosusuario; 
	//conect redes = new conect();
	RelativeLayout emp,conection;
	
	public HomeFragment() {
		
	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_home,container, false);

		/*mPullToRefreshLayout = (PullToRefreshLayout) rootView.findViewById(R.id.layoutTiendas);

		// We can now setup the PullToRefreshLayout
		ActionBarPullToRefresh
				.from(getActivity()) 
				.theseChildrenArePullable(R.id.listview,
						android.R.id.empty).listener(this)
				.setup(mPullToRefreshLayout);

		listado		= (ListView) rootView.findViewById(R.id.listview);
		emp 		= (RelativeLayout) rootView.findViewById(R.id.emp);
		conection 	= (RelativeLayout) rootView.findViewById(R.id.conection);*/
		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState); 
		
		// DAO SETTINGS
		/* 
		DevOpenHelper helper = new DaoMaster.DevOpenHelper(getActivity(),"cifal-db", null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession(); 
		usuarioDao = daoSession.getUsuarioDao();
		cursosusuariodao = daoSession.getCursosUsuario();
		cursorusuario = db.query(usuarioDao.getTablename(), usuarioDao.getAllColumns(), null, null, null, null,null);
		*/
		
		setHasOptionsMenu(true);
		
		/*if (cursorusuario.moveToNext()) {
			idusuario = cursorusuario.getString(0);
		} 
		*/
		
		/*if (redes.verificaConexion(getActivity())) {
			new CursosTask(idusuario, 0).execute();
		}else{
			if (cursosusuariodao.count()!=0) {
				cursorusuario = db.query(cursosusuariodao.getTablename(),
						cursosusuariodao.getAllColumns(), null, null, null, null,null);
				categoriasadapter = new ListaCategoriasAdapter(getActivity(), cursorusuario);
				listado.setAdapter(categoriasadapter);
				listado.setVerticalScrollBarEnabled(false);
			}else{
				emp.setVisibility(View.VISIBLE);
				Log.i("paso 2", "pasoooooo2");
			}
			
		}
		 
		listado.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startListaProductoActivity(position, cursorusuario);
			}
		});*/
		
	}
	
 
	/*private void startListaProductoActivity(int position, Cursor cursor2) {

		// position = position - 1;
		for (int k = 0; k < cursor2.getCount(); k++) {
			cursor2.moveToPosition(k);
			if (position == k) {
				Intent c = new Intent(getActivity(), DetalleCursoActivity.class);
				c.putExtra("idcurso", cursor2.getString(1));
				c.putExtra("idcategoria", cursor2.getString(7));
				c.putExtra("tituloCurso", cursor2.getString(2));
				c.putExtra("subtituloCurso", cursor2.getString(3));
				c.putExtra("imagenCurso", cursor2.getString(4));
				c.putExtra("descripcion", cursor2.getString(5));
				c.putExtra("contacto", cursor2.getString(6));
				startActivity(c);
			}
		}

	}
	 */

	@Override
	public void onRefreshStarted(View view) {
		// TODO Auto-generated method stub
		
	/*	if (cursorusuario.moveToNext()) {
			idusuario = cursorusuario.getString(0);
		}
		
		if (redes.verificaConexion(getActivity())) {
			new CursosTask(idusuario, 1 ).execute();
		}else{
			cursorusuario = db.query(cursosusuariodao.getTablename(),
					cursosusuariodao.getAllColumns(), null, null, null, null,
					null);
			categoriasadapter = new ListaCategoriasAdapter(getActivity(), cursorusuario);
			listado.setAdapter(categoriasadapter);
			listado.setVerticalScrollBarEnabled(false);
			mPullToRefreshLayout.setRefreshComplete();
		}*/

	} 
	
	/*@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu, inflater);		
		inflater.inflate(R.menu.main_search, menu);
		 	    
		SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(Menus.SEARCH));
	    searchView.setQueryHint(this.getString(R.string.search));
	    
	    ((EditText)searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text))
        .setHintTextColor(getResources().getColor(R.color.white));	    
	    searchView.setOnQueryTextListener(OnQuerySearchView);
					    	   	    	
		menu.findItem(Menus.SEARCH).setVisible(true);		
  	    
		searchCheck = false;
	}	*/
	
	
	
	/*public class CursosTask extends AsyncTask<Void, Void, Void> { 
		String token, iduser;
		View view;
		int error, value;  
		
		public CursosTask(String iduser, int value){
			this.iduser 	= iduser;
			this.value		= value;
		}
		
		@Override
		protected void onPreExecute()
		{
			try { 
				if (value==0) {
					cargar = new ProgressDialog(getActivity());
					cargar.setMessage("Loading ...");
					cargar.setCancelable(false);
					cargar.show();
				}
				 
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		protected Void doInBackground(Void... params) {
			// TODO Auto-generated method stub 
		 	if (usuarioDao.count()!=0) {
		 		getcursosusuario = new CI006_GetCursosUsuario(iduser);
			 	error = getcursosusuario.result;
			 	
			} else {
				getcursosusuario = new CI006_GetCursosUsuario("0");
			 	error = getcursosusuario.result;
			}
			
		 	if (error==0) 
		 	{
		 		cursosusuariodao.deleteAll();
		 		for (int i = 0; i < getcursosusuario.idorden.size(); i++) {
		 			BDCursosUsuario cursos = new BDCursosUsuario(getcursosusuario.idorden.get(i),
		 							getcursosusuario.idcurso.get(i), getcursosusuario.tituloCurso.get(i),
		 							getcursosusuario.subtituloCurso.get(i),getcursosusuario.imagenCurso.get(i), 
		 							getcursosusuario.descripcion.get(i),getcursosusuario.contacto.get(i), 
		 							getcursosusuario.idcategoria.get(i));
		 			
		 			cursosusuariodao.insert(cursos);
		 		} 
		 		
			}
			   
			return null;
		}
		
		@Override
		protected void onPostExecute(Void result) { 
			if (error==0) {
				cursorusuario = db.query(cursosusuariodao.getTablename(),
						cursosusuariodao.getAllColumns(), null, null, null, null,
						null);
				categoriasadapter = new ListaCategoriasAdapter(getActivity(), cursorusuario);
				listado.setAdapter(categoriasadapter);
				listado.setVerticalScrollBarEnabled(false);
			} else if(error==1){			
 				emp.setVisibility(View.GONE);
			}else if(error==2){			
 				emp.setVisibility(View.GONE);
			}else if(error==15){			
				Toast.makeText(getActivity(), "No connection!", Toast.LENGTH_LONG).show();
				conection.setVisibility(View.VISIBLE);
			}else{
				conection.setVisibility(View.VISIBLE);
			}
			
			if (value==0) {
				cargar.dismiss();
				
			} else {
				mPullToRefreshLayout.setRefreshComplete();
			}
			
			
		}
	}
	
	private OnQueryTextListener OnQuerySearchView = new OnQueryTextListener() {
			
		@Override
		public boolean onQueryTextSubmit(String arg0) {
			// TODO Auto-generated method stub
			if (!redes.verificaConexion(getActivity())) {
				Toast.makeText(getActivity(), "No internet connection available", Toast.LENGTH_SHORT).show();
			} else {
				Log.d("onQueryTextSubmit",arg0);
				Intent intent = new Intent(getActivity(), BusquedaActivity.class);
				intent.putExtra("busqueda", arg0);
				startActivity(intent);
			}
			
			return false;
		}
		
		@Override
		public boolean onQueryTextChange(String arg0) {
			// TODO Auto-generated method stub
			if (searchCheck){
				// implement your search here
			}
			return false;
		}
	};*/
 
}
