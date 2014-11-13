package com.imoves.pilar.adapter;

import com.imoves.pilar.R;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;



public class ListAliAdapter extends BaseAdapter {
	
	private LayoutInflater inflater;
	private final Context context;
	private final int[] bgColors = new int[] { R.color.item_bg_5, R.color.item_bg_6 };
	//DisplayImageOptions options;
	//protected ImageLoader imageLoader = ImageLoader.getInstance();
	//ObservatorioGetCategoria Categoria;
	String[] mCountries1,mCountries2;
	int[] flagsactivo;
	public ListAliAdapter(Context context, String[] mCountries1,String[] mCountries2,int[] flagsactivo) {
		
		//Categoria = new ObservatorioCategoriaWSOP003();
		
		//imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		//options = new DisplayImageOptions.Builder()
		// .showStubImage(R.drawable.logooo)
		// .showImageForEmptyUri(R.drawable.logooo)
		//		.cacheInMemory().cacheOnDisc()
		//		.displayer(new RoundedBitmapDisplayer(50))
		//		.build();
		
		this.inflater = LayoutInflater.from(context);
		this.context = context;	
		this.mCountries1 = mCountries1;
		this.mCountries2 = mCountries2;
		
		this.flagsactivo = flagsactivo;
	}
	

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mCountries1.length;
	}
	
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder;
		
		if (convertView == null) {
			view = inflater.inflate(R.layout.drawer_layout4, null);
			holder = new ViewHolder();
			holder.text1 = (TextView) view.findViewById(R.id.country1);
			holder.text2 = (TextView) view.findViewById(R.id.country2);
			holder.image = (ImageView) view.findViewById(R.id.flag);			
			view.setTag(holder);
		} else {
			holder = (ViewHolder) view.getTag();
			
		}
		//cursor.moveToPosition(position);
		
		int colorPosition = position % bgColors.length;
		view.setBackgroundResource(bgColors[colorPosition]);
		holder.text1.setText(mCountries1[position]);
		holder.text2.setText(mCountries2[position]);
		holder.image.setBackgroundResource(flagsactivo[position]);
		//imageLoader.displayImage(cursor.getString(2),holder.image, options);
		
		return view;
	}
	
	private class ViewHolder {
		public TextView text1,text2;
		public ImageView image;
	}
}
