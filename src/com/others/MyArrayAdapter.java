package com.others;

import java.util.ArrayList;

import com.classes.Advertiser;

import android.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

public class MyArrayAdapter extends ArrayAdapter<Advertiser> {

	private final Context context;
	private final ArrayList<Advertiser> advertisersArrayList;

	public MyArrayAdapter(Context context, ArrayList<Advertiser> values) {
		super(context, -1, values);
		this.context = context;
		this.advertisersArrayList = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// Step 1 create inflater
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		if(convertView == null){
			// Get the row view from the inflater
			convertView = inflater.inflate(com.main.fiveorless.R.layout.list_item, parent, false);
			ImageView icon = (ImageView) convertView.findViewById(com.main.fiveorless.R.id.icon_restaurant);
			
		}
		return null;
	}

}
