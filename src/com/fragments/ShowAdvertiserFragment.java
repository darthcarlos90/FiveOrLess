package com.fragments;

import java.util.Locale;

import com.classes.Advertiser;
import com.database.DatabaseHelper;
import com.database.ContractClass.Advertisers;
import com.main.fiveorless.MainViewActivity;
import com.main.fiveorless.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class ShowAdvertiserFragment extends Fragment {

	private final String ARG_NUMBER = "toSearch";
	private Advertiser advertiser;
	private DatabaseHelper myDatabase;
	private static final String TAG = "FIVEXLESS";
	private OnClickListener openMaps;

	/**
	 * Empty constructor
	 */
	public ShowAdvertiserFragment() {
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainViewActivity) activity).ManualOnSectionAttached(getArguments()
				.getString("advName"));
		openMaps = new OnClickListener() {

			@Override
			public void onClick(View v) {
				String uri = String.format(Locale.ENGLISH,
						"geo:%f,%f?z=17&q=%f,%f", advertiser.getLatitude(),
						advertiser.getLongitude(), advertiser.getLatitude(),
						advertiser.getLongitude());
				Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
				getActivity().startActivity(intent);
				Log.d(TAG, "Latitude: " + advertiser.getLatitude());
				Log.d(TAG, "Longitude: " + advertiser.getLongitude());
			}
		};

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_advertiser_view,
				container, false);
		// Initialize database
		myDatabase = new DatabaseHelper(getActivity());
		int id = getArguments().getInt(ARG_NUMBER);
		advertiser = new Advertiser(id);
		LoadContentDatabase();

		LoadView(rootView);

		return rootView;
	}

	/**
	 * Loads the info into the view
	 * 
	 * @param rootView
	 *            The root view of the layout.
	 */
	private void LoadView(View rootView) {
		// The views
		ImageView icon = (ImageView) rootView
				.findViewById(R.id.advertiser_main_image);
		TextView dishNameTv = (TextView) rootView
				.findViewById(R.id.dish_name_tv);
		TextView dishPriceTv = (TextView) rootView
				.findViewById(R.id.dish_price_tv);
		TextView dishDescriptionTv = (TextView) rootView
				.findViewById(R.id.dish_description_tv);
		TextView advertiserNameTv = (TextView) rootView
				.findViewById(R.id.advertiser_name_tv);
		TextView advertiserDetailedInfo = (TextView) rootView
				.findViewById(R.id.advertiser_detailed_info);
		TextView advertiserAddressTv = (TextView) rootView
				.findViewById(R.id.advertiser_address_tv);
		TextView advertiserPostcodeTv = (TextView) rootView
				.findViewById(R.id.advertiser_postcode_tv);
		ToggleButton favorites = (ToggleButton) rootView
				.findViewById(R.id.fav_button);

		int drawableId = getActivity().getResources().getIdentifier(
				advertiser.getShort_name() + "main", "drawable",
				getActivity().getPackageName());
		icon.setImageResource(drawableId);
		dishNameTv.setText(advertiser.getMainDish().getName());
		dishPriceTv.setText("Price: " + advertiser.getMainDish().getPrice());
		dishDescriptionTv.setText(advertiser.getMainDish().getDescription());
		advertiserNameTv.setText(advertiser.getName());
		advertiserDetailedInfo.setText(advertiser.getInfo());
		advertiserAddressTv.setText(advertiser.getAddress());
		advertiserPostcodeTv.setText(advertiser.getPostcode());
		if (advertiser.isIs_favorite()) {
			favorites.toggle();
		}

		favorites.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				SetFavorite(isChecked);

			}
		});

		advertiserAddressTv.setOnClickListener(openMaps);
		advertiserPostcodeTv.setOnClickListener(openMaps);

	}

	/**
	 * Gets the advertiser info from the database
	 */
	private void LoadContentDatabase() {
		SQLiteDatabase db = myDatabase.getReadableDatabase();
		String projection[] = { Advertisers.ADVERTISER_ID,
				Advertisers.DISPLAY_NAME, Advertisers.ADVERTISER_ADDRESS,
				Advertisers.ADVERTISER_LONGITUDE,
				Advertisers.ADVERTISER_LATITUDE, Advertisers.ADVERTISER_INFO,
				Advertisers.IS_FAVORITE, Advertisers.ADVERTISER_SHORT_NAME,
				Advertisers.DAY_TIME, Advertisers.POSTCODE };
		Cursor c = db.query(Advertisers.TABLE_NAME, projection,
				Advertisers.ADVERTISER_ID + " = " + advertiser.getId(), null,
				null, null, null, null);
		c.moveToFirst();

		advertiser.setName(c.getString(1));
		advertiser.setAddress(c.getString(2));
		advertiser.setLatitude(c.getDouble(3));
		advertiser.setLongitude(c.getDouble(4));
		advertiser.setInfo(c.getString(5));
		if (c.getInt(6) == 1) {
			advertiser.setIs_favorite(true);
		}
		advertiser.setShort_name(c.getString(7));
		advertiser.setImageLocation(advertiser.getShort_name() + "main.jpg");
		advertiser.setDaytime(c.getInt(8));
		advertiser.setPostcode(c.getString(9));
		c.close();

	}

	private void SetFavorite(boolean isFavorite) {
		int value = 0;
		if (isFavorite)
			value = 1;
		SQLiteDatabase db = myDatabase.getWritableDatabase();

		String strSql = "UPDATE " + Advertisers.TABLE_NAME + " SET "
				+ Advertisers.IS_FAVORITE + " = " + value + " WHERE "
				+ Advertisers.ADVERTISER_ID + " = '" + advertiser.getId() + "'";

		db.execSQL(strSql);
	}

}
