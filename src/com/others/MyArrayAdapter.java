package com.others;

import java.util.ArrayList;

import com.classes.Advertiser;
import com.main.fiveorless.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

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
		Advertiser adv = advertisersArrayList.get(position);
		// Step 1 create inflater
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		if (convertView == null) {
			// Get the row view from the inflater
			convertView = inflater.inflate(
					com.main.fiveorless.R.layout.list_item, parent, false);
			ImageView icon = (ImageView) convertView
					.findViewById(com.main.fiveorless.R.id.icon_restaurant);

			int drawableName = context.getResources().getIdentifier(
					adv.getShort_name() + "Main", "drawable",
					context.getPackageName());
			icon.setImageResource(drawableName);

			TextView name = (TextView) convertView
					.findViewById(R.id.restaurant_name_list_element);
			name.setText(adv.getName());

			TextView address = (TextView) convertView
					.findViewById(R.id.restaurant_address_list_element);
			address.setText(adv.getAddress());

			ToggleButton favorite = (ToggleButton) convertView
					.findViewById(R.id.is_favorite_toggle);
			if (adv.isIs_favorite()) {
				favorite.toggle();
			}

		}
		return convertView;
	}

}
