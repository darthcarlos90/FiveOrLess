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
		
		ViewHolder holder;

		// Get the row view from the inflater
		if (convertView == null) {
			// If it is the first element, inflate it
			convertView = inflater.inflate(R.layout.list_item, parent, false);
			holder = new ViewHolder();
			holder.icon = (ImageView) convertView.findViewById(R.id.icon_restaurant);
			holder.restaurantName = (TextView) convertView.findViewById(R.id.restaurant_name_list_element);
			holder.address = (TextView) convertView.findViewById(R.id.restaurant_address_list_element);
			holder.dishDescription = (TextView) convertView.findViewById(R.id.dish_description_list_element);
			holder.dishName = (TextView) convertView.findViewById(R.id.dish_name_list_element);
			holder.dishPrice = (TextView) convertView.findViewById(R.id.dish_price_list_element);
			holder.favorite = (ToggleButton) convertView.findViewById(R.id.is_favorite_toggle);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		int drawableName = context.getResources().getIdentifier(
				adv.getShort_name() + "main", "drawable",
				context.getPackageName());
		holder.icon.setImageResource(drawableName);
		holder.restaurantName.setText(adv.getName());
		holder.address.setText(adv.getAddress());
		if (adv.isIs_favorite()) {
			holder.favorite.toggle();
		}
		holder.dishDescription.setText(adv.getMainDish().getDescription());
		holder.dishName.setText(adv.getMainDish().getName());
		holder.dishPrice.setText("" + adv.getMainDish().getPrice());
		

		return convertView;
	}

}
