package com.main.fiveorless;

import com.fragments.BusinessListFragment;
import com.fragments.PreferencesFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import android.app.Activity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

public class MainViewActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks,
		ConnectionCallbacks, OnConnectionFailedListener, LocationListener {

	private static final String TAG = "FIVEXLESS";
	private static final String ARG_SECTION_NUMBER = "section_number";
	protected GoogleApiClient gac;
	protected Location mLocation;
	int counter = 0;
	private LocationManager lm;
	private double longitude;
	private double latitude;

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_view);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));

		lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

		latitude = 0.0;
		longitude = 0.0;

		/*
		 * Thanks stack overflow!!!!
		 * http://stackoverflow.com/questions/10311834/
		 * how-to-check-if-location-services-are-enabled
		 */
		boolean gps_enabled = false;
		boolean network_enabled = false;

		gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
		network_enabled = lm
				.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

		if (!gps_enabled && !network_enabled) {
			AlertDialog.Builder dialog = new AlertDialog.Builder(this);
			dialog.setMessage(this.getResources().getString(
					R.string.no_gps_message));
			dialog.setPositiveButton(
					this.getResources().getString(R.string.go_settings_txt),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent myIntent = new Intent(
									Settings.ACTION_LOCATION_SOURCE_SETTINGS);
							startActivity(myIntent);

						}
					});
			dialog.setNegativeButton("Stay like this",
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub

						}
					});

			dialog.show();
		}

		buildGoogleApiClient();
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		transaction.replace(R.id.container, createFragment(position + 1));
		if (counter > 0) {
			transaction.addToBackStack(null);
		}
		counter++;
		transaction.commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.title_section_main);
			break;
		case 2:
			mTitle = getString(R.string.title_section_show);
			break;
		case 3:
			mTitle = getString(R.string.title_section_favorites);
			break;
		case 4:
			mTitle = getString(R.string.title_section_options);
			break;
		case 5:
			mTitle = getString(R.string.title_section_feedback);
			break;
		}
		getActionBar().setTitle(mTitle);

	}

	public void ManualOnSectionAttached(String name) {
		mTitle = name;
		getActionBar().setTitle(name);
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main_view, menu);
			restoreActionBar();
			// Log.d(TAG, "onCreateOptionsMenu");
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private Fragment createFragment(int sectionNumber) {
		// TODO: Add functionality to the fragment stack
		Fragment fragment = null;
		Bundle args = new Bundle();

		// TODO: Leave the switch for now, but in the future, a simple if
		// statement will sufice
		switch (sectionNumber) {
		case 1:
			fragment = new BusinessListFragment();
			break;
		case 2:
			fragment = new BusinessListFragment();

			/*
			 * If at this point the locations are null, then this means that the
			 * location services have been deactivated.
			 */
			if (mLocation != null) {
				latitude = mLocation.getLatitude();
				longitude = mLocation.getLongitude();
			}

			args.putDouble("Latitude", latitude);
			args.putDouble("Longitude", longitude);

			break;
		case 3:
			fragment = new BusinessListFragment();
			break;
		case 4:
			fragment = new PreferencesFragment();
			break;

		}

		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);

		return fragment;

	}

	protected synchronized void buildGoogleApiClient() {
		gac = new GoogleApiClient.Builder(this).addConnectionCallbacks(this)
				.addOnConnectionFailedListener(this)
				.addApi(LocationServices.API).build();
	}

	@Override
	protected void onStart() {
		super.onStart();
		gac.connect();
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (gac.isConnected()) {
			gac.disconnect();
		}
	}

	@Override
	protected void onPause() {
		super.onPause();
		lm.removeUpdates(this);
	}

	/**
	 * When a google api client is succesfully connected.
	 */
	@Override
	public void onConnected(Bundle connectionHint) {
		/*
		 * This gets the last location get maybe by us, maybe by some other app.
		 * This will return null if this is the first app trying to get the
		 * location.
		 */
		mLocation = LocationServices.FusedLocationApi.getLastLocation(gac);
		if (mLocation == null) {
			/*
			 * If no one else got the location, then is time to get it by
			 * ourselves. Damn you other apps!!!
			 */
			Criteria criteria = new Criteria();
			String bestProvider = String.valueOf(
					lm.getBestProvider(criteria, true)).toString();

			lm.requestLocationUpdates(bestProvider, 1000, 0, this);
		} else {
			Log.d(TAG, "Latitude: " + mLocation.getLatitude());
			Log.d(TAG, "Longitude: " + mLocation.getLongitude());
		}

	}

	@Override
	public void onConnectionSuspended(int cause) {
		Log.i(TAG, "Connection suspended");
		gac.connect();
	}

	@Override
	public void onConnectionFailed(ConnectionResult result) {
		Log.i(TAG, "Connection failed: ConnectionResult.getErrorCode() = "
				+ result.getErrorCode());

	}

	@Override
	public void onLocationChanged(Location location) {
		// We got a not null location
		lm.removeUpdates(this); // remove updates so we dont drain battery

		// now update values
		latitude = location.getLatitude();
		longitude = location.getLongitude();

	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub

	}

}
