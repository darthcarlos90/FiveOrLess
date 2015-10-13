package com.fragments;

import com.main.fiveorless.MainViewActivity;

import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class PreferencesFragment extends PreferenceFragment {

	/*
	 * Copy paste because, well, fuck it
	 */
	protected final String ARG_SECTION_NUMBER = "section_number";
	int argument_number;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(com.main.fiveorless.R.xml.preferences_layout);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		argument_number = getArguments().getInt(ARG_SECTION_NUMBER);
		((MainViewActivity) activity).onSectionAttached(argument_number);
	}

	@Override
	public void onResume() {
		super.onResume();
		((MainViewActivity) getActivity()).onSectionAttached(argument_number);
	}

}
