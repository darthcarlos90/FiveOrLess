package com.fragments;

import java.util.ArrayList;

import com.classes.Advertiser;
import com.classes.Dish;
import com.classes.SearchManager;
import com.main.fiveorless.R;
import com.others.MyArrayAdapter;
import com.database.*;
import com.database.ContractClass.Advertisers;
import com.database.ContractClass.Dishes;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class BusinessListFragment extends ParentFragmentClass {

	private final String ARG_EVERYTHING = "section_number";
	private ArrayList<Advertiser> advertisers;
	private DatabaseHelper myDatabase;
	private static final String TAG = "FIVEXLESS";

	public BusinessListFragment() {
		// Empty constructor needed!
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		boolean showList = true;
		myDatabase = new DatabaseHelper(getActivity());
		advertisers = new ArrayList<Advertiser>();
		View rootView = inflater.inflate(R.layout.fragment_lists_view,
				container, false);
		int type = getArguments().getInt(ARG_EVERYTHING);
		SQLiteDatabase db = myDatabase.getReadableDatabase();

		switch (type) {
		case 1:
			// Get all the advertisers from the data base and save it on the
			// advertisers array list
			advertisers = getAll(db);
			break;
		case 2:
			// Show only the elements that are around
			double latitude = getArguments().getDouble("Latitude");
			double longitude = getArguments().getDouble("Longitude");
			if (latitude == 0.0 && longitude == 0.0) {
				// If the latitude and longitude are 0, just show a message
				showList = false;

			} else {
				/*
				 * TODO: This thing of getting all advertisers should change,
				 * maybe when a proper server is established, send the location
				 * through the web service, and allow the web service to do the
				 * calculations, and retrieve only the correct elements.
				 */
				ArrayList<Advertiser> temp = getAll(db); // get all the
															// advertisers
				// calculate which elements are in a distance radius
				SharedPreferences preferences = PreferenceManager
						.getDefaultSharedPreferences(getActivity());
				double distance = Double.parseDouble(preferences.getString(
						"distance_settings", "1.0"));
				String units = preferences.getString("unit_settings", "km");
				Log.d(TAG, distance + "");
				Log.d(TAG, units);
				// convert to km if the units are not kilometers
				if (units.equals("m")) {
					distance = distance / 1000.0;
				} else if (units.equals("mi")) {
					distance *= 1.609;
				}

				advertisers = SearchManager.businesses(temp, latitude,
						longitude, distance);
			}

			break;
		case 3:
			// Do a query of all the favorites
			advertisers = getFavorites(db);
			break;
		}

		if (!showList) {
			TextView tv = (TextView) rootView.findViewById(R.id.section_label);
			tv.setText("Please enable your location services to enable this feature.");

		} else {
			// Let's see how many elements are around the location
			if (advertisers.size() > 0) {
				// Now that we've got the data, lets put it on the List View
				MyArrayAdapter adapter = new MyArrayAdapter(getActivity(),
						advertisers);

				ListView listView = (ListView) rootView
						.findViewById(R.id.main_list);
				listView.setAdapter(adapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						FragmentManager fragmentManager = getFragmentManager();
						Fragment fragment = new ShowAdvertiserFragment();
						Bundle args = new Bundle();
						args.putInt("toSearch", advertisers.get(position)
								.getId());
						args.putString("advName", advertisers.get(position)
								.getName());
						fragment.setArguments(args);
						fragmentManager.beginTransaction()
								.replace(R.id.container, fragment)
								.addToBackStack(null).commit();
					}
				});

			} else {
				TextView tv = (TextView) rootView
						.findViewById(R.id.section_label);
				tv.setText("No restaurants arround :(");
			}

		}

		return rootView;
	}

	private ArrayList<Advertiser> getFavorites(SQLiteDatabase db) {
		ArrayList<Advertiser> result = new ArrayList<Advertiser>();
		String projection[] = { Advertisers.ADVERTISER_ID,
				Advertisers.DISPLAY_NAME, Advertisers.ADVERTISER_ADDRESS,
				Advertisers.ADVERTISER_LONGITUDE,
				Advertisers.ADVERTISER_LATITUDE, Advertisers.ADVERTISER_INFO,
				Advertisers.IS_FAVORITE, Advertisers.ADVERTISER_SHORT_NAME,
				Advertisers.DAY_TIME, Advertisers.POSTCODE };
		Cursor c = db.query(Advertisers.TABLE_NAME, projection,
				Advertisers.IS_FAVORITE + " = 1", null, null, null, null, null);
		// c.moveToFirst();
		while (c.moveToNext()) {
			Advertiser adv = new Advertiser(c.getInt(0), c.getString(1),
					c.getString(2), c.getFloat(3), c.getFloat(4),
					c.getString(5), c.getInt(6), c.getString(7), c.getInt(8),
					c.getString(9));
			adv.setDishes(getDishes(db, adv.getId()));
			result.add(adv);

		}

		c.close();

		return result;

	}

	private ArrayList<Advertiser> getAll(SQLiteDatabase db) {
		ArrayList<Advertiser> result = new ArrayList<Advertiser>();
		String projection[] = { Advertisers.ADVERTISER_ID,
				Advertisers.DISPLAY_NAME, Advertisers.ADVERTISER_ADDRESS,
				Advertisers.ADVERTISER_LONGITUDE,
				Advertisers.ADVERTISER_LATITUDE, Advertisers.ADVERTISER_INFO,
				Advertisers.IS_FAVORITE, Advertisers.ADVERTISER_SHORT_NAME,
				Advertisers.DAY_TIME, Advertisers.POSTCODE };

		Cursor c = db.query(Advertisers.TABLE_NAME, projection, null, null,
				null, null, null, null);
		c.moveToFirst();
		do {
			Advertiser adv = new Advertiser(c.getInt(0), c.getString(1),
					c.getString(2), c.getFloat(3), c.getFloat(4),
					c.getString(5), c.getInt(6), c.getString(7), c.getInt(8),
					c.getString(9));
			// adv.print();
			adv.setDishes(getDishes(db, adv.getId()));
			result.add(adv);

		} while (c.moveToNext());

		c.close();

		return result;

	}

	private ArrayList<Dish> getDishes(SQLiteDatabase db, int advertiserId) {
		ArrayList<Dish> result = new ArrayList<Dish>();
		String dish_projection[] = { Dishes.DISH_ID, Dishes.DISH_NAME,
				Dishes.DISH_INFO, Dishes.DISH_PRICE };
		Cursor dishCursor = db.query(Dishes.TABLE_NAME, dish_projection,
				Dishes.ADVERTISER_ID + " = " + advertiserId, null, null, null,
				null, null);
		while (dishCursor.moveToNext()) {
			Dish d = new Dish(dishCursor.getInt(0), dishCursor.getString(1),
					dishCursor.getDouble(3), dishCursor.getString(2), true);
			result.add(d);
		}

		dishCursor.close();

		return result;
	}
}
