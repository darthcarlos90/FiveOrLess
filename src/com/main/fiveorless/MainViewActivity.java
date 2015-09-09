package com.main.fiveorless;


import com.fragments.OtherFragmentTest;

import android.app.Activity;

import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.webkit.WebView.FindListener;
import android.widget.ArrayAdapter;
import android.widget.TextView;


//TODO: Update the ADT plugin
public class MainViewActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks {
	
	private static final String TAG = "FIVEXLESS";

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
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager
				.beginTransaction()
				.replace(R.id.container,
						createFragment(position + 1)).commit();
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
		
		Log.d(TAG, "onSectionAttached " + (String)mTitle);
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
	
	private Fragment createFragment (int sectionNumber){
		if(sectionNumber == 1){
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(fragment.ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		} else {
			OtherFragmentTest otherFragment = new OtherFragmentTest();
			Bundle args = new Bundle();
			args.putInt(otherFragment.ARG_SECTION_NUMBER, sectionNumber);
			otherFragment.setArguments(args);
			return otherFragment;
		}
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		public final String ARG_SECTION_NUMBER = "section_number";
		private static int section_number = -1;
		
		/**
		 * Constructor
		 */
		public PlaceholderFragment() {
			/*
			 * Empty constructor required for fragment sub classes
			 */
		}

//		/**
//		 * Returns a new instance of this fragment for the given section number.
//		 */
//		public static PlaceholderFragment newInstance(int sectionNumber) {
//			PlaceholderFragment fragment = new PlaceholderFragment();
//			Bundle args = new Bundle();
//			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
//			section_number = sectionNumber;
//			fragment.setArguments(args);
//			return fragment;
//		}

		

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			
			View rootView = inflater.inflate(R.layout.fragment_main_view,
					container, false);
			TextView tv = (TextView) rootView.findViewById(R.id.section_label);
			tv.setText("" + section_number);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MainViewActivity) activity).onSectionAttached(getArguments()
					.getInt(ARG_SECTION_NUMBER));
		}
	}

}
