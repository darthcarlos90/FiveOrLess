package com.others;

import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

/**
 * This class is used as a container for all the views inside the custom layout.
 * 
 * @author Carlos Tirado
 * 
 */
public class ViewHolder {

	public ImageView icon;
	public TextView dishName;
	public TextView dishPrice;
	public TextView dishDescription;
	public TextView restaurantName;
	public TextView address;
	public ToggleButton favorite;

	// Empty constructor
	public ViewHolder() {
	}

}
