package com.others;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class MyArrayAdapter extends ArrayAdapter<String>{
	
	private final Context context;
	private final String[] ids;
	
	public MyArrayAdapter (Context context, String[] values){
		super(context, -1, values);
		this.context = context;
		this.ids = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		return super.getView(position, convertView, parent);
	}
	
	

}
