package com.fragments;


import com.main.fiveorless.R;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class BusinessListFragment extends ParentFragmentClass {
	
	private final String ARG_EVERYTHING = "everything";
	
	public BusinessListFragment(){
		// Empty constructor needed!
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main_view,
				container, false);
		TextView mainText = (TextView) rootView.findViewById(R.id.section_label);
		boolean everything = getArguments().getBoolean(ARG_EVERYTHING);
		// TODO: Improve this when a proper database is created
		if(everything){
			mainText.setText("Everything!!!");
		}else {
			mainText.setText("Business around");
		}
		return rootView;
	}
	
	

}
