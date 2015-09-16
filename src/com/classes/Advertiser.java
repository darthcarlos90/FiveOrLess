package com.classes;

import java.util.List;

public class Advertiser {

	private int id;
	private String name;
	private String address;
	private String postcode;
	private float x_location;
	private float y_location;
	private String info;
	private boolean is_favorite;
	private String short_name;
	private boolean has_discount;
	private String discountInfo;
	private String mainImageLocation;
	private List<String> otherImages;
	private int daytime;

	/**
	 * The constructor of the advertiser class.
	 * 
	 * @param id
	 *            The id of the advertiser.
	 * @param name
	 *            The name of the advertiser
	 * @param address
	 *            The address of the advertiser
	 * @param x
	 *            The x location of the advertiser
	 * @param y
	 *            The y location of the advertiser
	 * @param info
	 *            The information of the advertiser
	 * @param fav
	 *            If it is favorite or not
	 * @param short_name
	 *            The short name of the advertiser
	 * @param daytime
	 *            For what kind of meal is this advertiser
	 * @param postcode
	 *            The postcode of the advertiser
	 */
	public Advertiser(int id, String name, String address, float x, float y,
			String info, int fav, String short_name, int daytime,
			String postcode) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.x_location = x;
		this.y_location = y;
		this.info = info;
		if (fav == 0) {
			this.is_favorite = false;
		} else
			this.is_favorite = true;

		this.short_name = short_name;
		has_discount = false;
		discountInfo = "";
		this.mainImageLocation = short_name + "Main.jpg";
		this.daytime = daytime;
		this.postcode = postcode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public float getX_location() {
		return x_location;
	}

	public void setX_location(float x_location) {
		this.x_location = x_location;
	}

	public float getY_location() {
		return y_location;
	}

	public void setY_location(float y_location) {
		this.y_location = y_location;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isIs_favorite() {
		return is_favorite;
	}

	public void setIs_favorite(boolean is_favorite) {
		this.is_favorite = is_favorite;
	}

	public String getShort_name() {
		return short_name;
	}

	public void setShort_name(String short_name) {
		this.short_name = short_name;
	}

	public boolean hasDiscount() {
		return has_discount;
	}

	public String getDiscount() {
		return discountInfo;
	}

	public void setDiscount(String discount) {
		this.discountInfo = discount;
		has_discount = true;
	}

	public String getImageLocation() {
		return mainImageLocation;
	}

	public void setImageLocation(String imageLocation) {
		this.mainImageLocation = imageLocation;
	}

	public int getDaytime() {
		return daytime;
	}

	public void setDaytime(int daytime) {
		this.daytime = daytime;
	}

	public void addImage(String image_location) {
		otherImages.add(image_location);
	}

	public String getImageAt(int index) {
		return otherImages.get(index);
	}

	public List<String> getAllSecondaryImages() {
		return otherImages;
	}

	public String getPostcode() {
		return postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

}
