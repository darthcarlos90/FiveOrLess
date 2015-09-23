package com.fragments;

import java.util.ArrayList;

import com.classes.Advertiser;
import com.classes.SearchManager;
import com.main.fiveorless.R;
import com.others.MyArrayAdapter;
import com.database.*;
import com.database.ContractClass.Advertisers;

import android.app.Fragment;
import android.app.FragmentManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BusinessListFragment extends ParentFragmentClass {

	private final String ARG_EVERYTHING = "section_number";
	private ArrayList<Advertiser> advertisers;
	private DatabaseHelper myDatabase;

	public BusinessListFragment() {
		// Empty constructor needed!
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
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
			ArrayList<Advertiser> temp = getAll(db); // get all the advertisers
			// calculate which elements are in a distance radius
			// TODO: Set the distance to be an option of the app
			advertisers = SearchManager
					.businesses(temp, latitude, longitude, 1);
			break;
		case 3:
			// Do a query of all the favorites
			advertisers = getFavorites(db);
			break;
		}

		// Now that we've got the data, lets put it on the List View
		MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), advertisers);

		ListView listView = (ListView) rootView.findViewById(R.id.main_list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				FragmentManager fragmentManager = getFragmentManager();
				Fragment fragment = new ShowAdvertiserFragment();
				Bundle args = new Bundle();
				args.putInt("toSearch", advertisers.get(position).getId());
				fragment.setArguments(args);
				fragmentManager.beginTransaction()
						.replace(R.id.container, fragment).commit();
			}
		});

		return rootView;
	}

	private ArrayList<Advertiser> getFavorites(SQLiteDatabase db) {
		ArrayList<Advertiser> result = new ArrayList<Advertiser>();
		String projection[] = { Advertisers.ADVERTISER_ID,
				Advertisers.DISPLAY_NAME, Advertisers.ADVERTISER_ADDRESS,
				Advertisers.ADVERTISER_X_LOCATION,
				Advertisers.ADVERTISER_Y_LOCATION, Advertisers.ADVERTISER_INFO,
				Advertisers.IS_FAVORITE, Advertisers.ADVERTISER_SHORT_NAME,
				Advertisers.DAY_TIME, Advertisers.POSTCODE };
		Cursor c = db.query(Advertisers.TABLE_NAME, projection,
				Advertisers.IS_FAVORITE + " = 1", null, null, null, null, null);
		c.moveToFirst();
		while (c.moveToNext()) {
			Advertiser adv = new Advertiser(c.getInt(0), c.getString(1),
					c.getString(2), c.getFloat(3), c.getFloat(4),
					c.getString(5), c.getInt(6), c.getString(7), c.getInt(8),
					c.getString(9));
			// adv.print();
			result.add(adv);

		}

		return result;

	}

	private ArrayList<Advertiser> getAll(SQLiteDatabase db) {
		ArrayList<Advertiser> result = new ArrayList<Advertiser>();
		String projection[] = { Advertisers.ADVERTISER_ID,
				Advertisers.DISPLAY_NAME, Advertisers.ADVERTISER_ADDRESS,
				Advertisers.ADVERTISER_X_LOCATION,
				Advertisers.ADVERTISER_Y_LOCATION, Advertisers.ADVERTISER_INFO,
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
			result.add(adv);

		} while (c.moveToNext());

		return result;

	}
}
