package com.fragments;

import com.main.fiveorless.R;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class FavoritesFragment extends Fragment {

	public FavoritesFragment(){} // Empty constructor

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView  = inflater.inflate(R.layout.fragment_lists_view,
				container, false);
		TextView tv = (TextView) rootView.findViewById(R.id.section_label);
		tv.setText("Favorites list");
		
		return rootView;
	}
	
	
	
}
