package com.fragments;

import com.main.fiveorless.MainViewActivity;
import com.main.fiveorless.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class OtherFragmentTest extends Fragment {
	
	/**
	 * The fragment argument representing the section number for this
	 * fragment.
	 */
	public final String ARG_SECTION_NUMBER = "section_number";
	
	public OtherFragmentTest(){
		// Empty constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.test_xml, container, false);
		return rootView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainViewActivity) activity).onSectionAttached(getArguments()
				.getInt(ARG_SECTION_NUMBER));
	}
	
	
	

}
