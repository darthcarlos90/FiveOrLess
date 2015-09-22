package com.classes;

import java.util.ArrayList;

public class SearchManager {

	private static Double EARTH_RADIUS = 6371.00;

	private static Double calculateDistance(Double lat1, Double lon1,
			Double lat2, Double lon2) {
		Double Radius = EARTH_RADIUS; // 6371.00;
		Double dLat = Math.toRadians(lat2 - lat1);
		Double dLon = Math.toRadians(lon2 - lon1);
		Double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(Math.toRadians(lat1))
				* Math.cos(Math.toRadians(lat2)) * Math.sin(dLon / 2)
				* Math.sin(dLon / 2);
		Double c = (2 * Math.asin(Math.sqrt(a)));
		return (Radius * c);
	}

	public static ArrayList<Advertiser> businesses(ArrayList<Advertiser> all,
			double latitude, double longitude, double distance) {

		ArrayList<Advertiser> result = new ArrayList<Advertiser>();
		for (int i = 0; i < all.size(); i++) {
			double km = calculateDistance(latitude, longitude, all.get(i)
					.getLatitude(), all.get(i).getLongitude());
			if (km < distance) { 
				result.add(all.get(i));
			}
		}
		return result;
	}

}
