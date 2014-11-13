package com.imoves.pilar.fragment;

import com.imoves.pilar.R;
import com.imoves.pilar.activity.DetalleActivity;
import com.imoves.pilar.adapter.ListAdapter;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;



public class DirectorioFragment extends Fragment implements
		OnRefreshListener {
	private PullToRefreshLayout mPullToRefreshLayout;
	ListAdapter listdapter;
	ListView listado;
	RelativeLayout emp,conection;
	 String[] mCountries ;
	//CI007_GetCursosCat cat;
	//conect conect = new conect();
	/*private SQLiteDatabase db;
	private DaoMaster daoMaster;
	private DaoSession daoSession;
	private BDCursoCategoriaDao cursoDao;*/
	public ProgressDialog loading;
	public Cursor cursor;

	public DirectorioFragment() {

	}

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_list,
				container, false);

		mPullToRefreshLayout = (PullToRefreshLayout) rootView.findViewById(R.id.layoutTiendas);
		//emp 		= (RelativeLayout) rootView.findViewById(R.id.emp);
		//conection 	= (RelativeLayout) rootView.findViewById(R.id.conection);
		mCountries = getResources().getStringArray(R.array.drawer1);
		listdapter = new ListAdapter(getActivity(), mCountries);
		listado 	= (ListView) rootView.findViewById(R.id.listview);
		listado.setAdapter(listdapter);
		// We can now setup the PullToRefreshLayout
		ActionBarPullToRefresh
				.from(getActivity())
				// We need to insert the PullToRefreshLayout into the Fragment's
				// ViewGroup
				// .insertLayoutInto(this)
				// Here we mark just the ListView and it's Empty View as
				// pullable
				.theseChildrenArePullable(R.id.listview, android.R.id.empty)
				.listener(this).setup(mPullToRefreshLayout);

		/*DevOpenHelper helper = new DaoMaster.DevOpenHelper(getActivity(),
				"cifal-db", null);
		db = helper.getWritableDatabase();
		daoMaster = new DaoMaster(db);
		daoSession = daoMaster.newSession();
		cursoDao = daoSession.getCursoCategoriaDao();*/

		return rootView;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		/*if (!conect.verificaConexion(getActivity())) {
			if (cursoDao.count() != 0) {
				cursor = db.query(cursoDao.getTablename(),
						cursoDao.getAllColumns(), "idcategoria = 2", null, null, null,
						null);
				categoriasadapter = new ListaUrbanGovernanceAdapter(getActivity(), cursor);
				listado.setAdapter(categoriasadapter);
				listado.setVerticalScrollBarEnabled(false);
			}else {
				emp.setVisibility(View.VISIBLE);
				Log.i("paso 2", "pasoooooo2");
			}
		} else {
			new GetDataTask(0,"2").execute();
		}*/

		listado.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				startListaProductoActivity(position, cursor);
			}
		});	

	}
	
	private void startListaProductoActivity(int position, Cursor cursor2) {

		// position = position - 1;
		for (int k = 0; k < mCountries.length; k++) {
			if (position == k) {
				Intent c = new Intent(getActivity(), DetalleActivity.class);
				startActivity(c);
			}
		}

	}

	@Override
	public void onRefreshStarted(View view) {
		// TODO Auto-generated method stub
		//new GetDataTask(1,"2").execute();
	}
	
	/*private class GetDataTask extends AsyncTask<Void, Void, Void> {

		int resultado,r;
		String idcategoria;		
		
		public GetDataTask(int r,String idcategoria) {
			this.r = r;
			this.idcategoria = idcategoria;
		}

		@Override
		protected void onPreExecute() {
			if (r != 1) {
				try {
					loading = new ProgressDialog(getActivity());
					loading.setMessage("Loading");
					loading.setCancelable(false);
					loading.show();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		@Override
		protected Void doInBackground(Void... arg0) {
			// TODO Auto-generated method stub
			
				cat = new CI007_GetCursosCat(idcategoria);
				resultado = cat.result;
				if(resultado == 0){
					db.delete("CURSO_CATEGORIA", "idcategoria = "+idcategoria, null);
					for (int i = 0; i < cat.idcurso.size(); i++) {
						BDCursoCategoria miembro = new BDCursoCategoria(
								cat.idcurso.get(i), 
								cat.idorden.get(i), 
								"2",
								cat.tituloCurso.get(i),
								cat.subtituloCurso.get(i),
								cat.imagenCurso.get(i),
								cat.descripcion.get(i),
								cat.contacto.get(i));
						cursoDao.insert(miembro);
					}
				}
			return null;
		}
		 
		@Override
		protected void onPostExecute(Void result) {
			if(resultado==0){
				//sessionMiembroDao.deleteAll();
				conection.setVisibility(View.GONE);
				emp.setVisibility(View.GONE);
				cursor = db.query(cursoDao.getTablename(),
						cursoDao.getAllColumns(), "idcategoria = "+idcategoria, null, null, null,
						null);
				categoriasadapter = new ListaUrbanGovernanceAdapter(getActivity(), cursor);
				listado.setAdapter(categoriasadapter);
				listado.setVerticalScrollBarEnabled(false);
			}else if(resultado==1){			
 				emp.setVisibility(View.GONE);
			}else if(resultado==2){			
 				emp.setVisibility(View.GONE);
			}else if(resultado==15){			
				Toast.makeText(getActivity(), "No connection!", Toast.LENGTH_LONG).show();
				conection.setVisibility(View.VISIBLE);
			}else{
				conection.setVisibility(View.VISIBLE);
			}
			if (r != 1) {
				loading.dismiss();
			} else {
				mPullToRefreshLayout.setRefreshComplete();
			}
		}
		
	}
*/
}
