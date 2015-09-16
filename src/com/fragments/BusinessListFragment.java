package com.fragments;

import java.util.ArrayList;

import com.classes.Advertiser;
import com.main.fiveorless.R;
import com.others.MyArrayAdapter;
import com.database.*;
import com.database.ContractClass.Advertisers;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class BusinessListFragment extends ParentFragmentClass {

	private final String ARG_EVERYTHING = "everything";
	private ArrayList<Advertiser> advertisers;
	private DatabaseHelper myDatabase = new DatabaseHelper(getActivity());

	public BusinessListFragment() {
		// Empty constructor needed!
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_lists_view,
				container, false);
		TextView mainText = (TextView) rootView
				.findViewById(R.id.section_label);
		boolean everything = getArguments().getBoolean(ARG_EVERYTHING);
		SQLiteDatabase db = myDatabase.getReadableDatabase();

		if (everything) {
			// Get all the advertisers from the data base
			String projection[] = { Advertisers.ADVERTISER_ID,
					Advertisers.DISPLAY_NAME, Advertisers.ADVERTISER_ADDRESS,
					Advertisers.ADVERTISER_X_LOCATION,
					Advertisers.ADVERTISER_Y_LOCATION,
					Advertisers.ADVERTISER_INFO, Advertisers.IS_FAVORITE,
					Advertisers.ADVERTISER_SHORT_NAME, Advertisers.DAY_TIME,
					Advertisers.POSTCODE };

			Cursor c = db.query(Advertisers.TABLE_NAME, projection, null, null,
					null, null, null, null);
			c.moveToFirst();
			do {
				Advertiser adv = new Advertiser(c.getInt(0), c.getString(1),
						c.getString(2), c.getFloat(3), c.getFloat(4),
						c.getString(5), c.getInt(6), c.getString(7),
						c.getInt(8), c.getString(9));
				advertisers.add(adv);

			} while (c.moveToNext());

		} else {
			mainText.setText("Business around");
		}
		
		// Now that we've got the data, lets put it on the List View
		MyArrayAdapter adapter = new MyArrayAdapter(getActivity(), advertisers);
		
		ListView listView = (ListView)rootView.findViewById(R.id.main_list);
		listView.setAdapter(adapter);
		
		return rootView;
	}
}
