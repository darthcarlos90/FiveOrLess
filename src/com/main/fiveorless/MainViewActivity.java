package com.main.fiveorless;

import com.fragments.FavoritesFragment;
import com.fragments.BusinessListFragment;
import com.fragments.ShowAdvertiserFragment;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.location.LocationServices;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;

public class MainViewActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks,
		ConnectionCallbacks, OnConnectionFailedListener {

	private static final String TAG = "FIVEXLESS";
	private static final String ARG_SECTION_NUMBER = "section_number";
	protected GoogleApiClient gac;
	protected Location mLocation;

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
		
		buildGoogleApiClient();
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.container, createFragment(position + 1)).commit();
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
	}

	public void ManualOnSectionAttached(String name) {
		mTitle = name;
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
			Log.d(TAG, "onCreateOptionsMenu");
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
		Fragment fragment = null;
		Bundle args = new Bundle();

		switch (sectionNumber) {
		case 1:
			fragment = new BusinessListFragment();
			break;
		case 2:
			fragment = new BusinessListFragment();
			args.putDouble("Latitude", mLocation.getLatitude());
			args.putDouble("Longitude", mLocation.getLongitude());
			break;
		case 3:
			fragment = new FavoritesFragment();
			break;
		case 4:
			/*
			 * TODO: This is a test, it should be removed from the code when the
			 * lists are somewhat ready.
			 */
			fragment = new ShowAdvertiserFragment();
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

	/**
	 * When a google api client is succesfully connected.
	 */
	@Override
	public void onConnected(Bundle connectionHint) {
		/*
		 * Apparently, the best practice is to get the location this way when
		 * not a totally accurate location is needed, and when you dont need to
		 * be getting exact location updates every other time.
		 */

		mLocation = LocationServices.FusedLocationApi.getLastLocation(gac);
		Log.d(TAG, "Latitude: " + mLocation.getLatitude());
		Log.d(TAG, "Longitude: " + mLocation.getLongitude());

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

}
