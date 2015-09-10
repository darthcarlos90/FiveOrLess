package com.fragments;

import com.main.fiveorless.MainViewActivity;
import com.main.fiveorless.R;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ShowAdvertiserFragment extends Fragment {
	
	private String advertiserName = "";

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainViewActivity) activity).ManualOnSectionAttached(advertiserName);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView  = inflater.inflate(R.layout.fragment_advertiser_view,
				container, false);
		
		return rootView;
	}
	

}
