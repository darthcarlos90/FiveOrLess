package com.fragments;

import com.main.fiveorless.MainViewActivity;

import android.app.Activity;
import android.app.Fragment;

/**
 * Parent Fragment class where some functionality that is expected from every
 * fragment class is stored.
 * 
 * @author Carlos Tirado
 * 
 */

public class ParentFragmentClass extends Fragment {

	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	protected final String ARG_SECTION_NUMBER = "section_number";
	int argument_number;

	public ParentFragmentClass() {
		// Empty constructor
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
