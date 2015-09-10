package com.database;

import android.provider.BaseColumns;

public abstract class ContractClass {
	public ContractClass(){}
	
	public static abstract class Advertisers implements BaseColumns{
		public static final String TABLE_NAME = "Advertisers";
		public static final String ADVERTISER_ID = "AdvertiserId";
		public static final String ADVERTISER_NAME = "AdvertiserName";
		public static final String ADVERTISER_LOCATION = "AdvertiserLocation";
		public static final String ADVERTISER_INFO = "AdvertiserInfo";
		public static final String IS_FAVORITE = "IsFavorite";
	}
	
	public static abstract class Discounts implements BaseColumns{
		public static final String TABLE_NAME = "Discounts";
		public static final String DISCOUNT_ID = "DiscountId";
		// Is this even correct? lol
		public static final String ADVERTISER_ID = Advertisers.ADVERTISER_ID;
		public static final String DISCOUNT_INFO = "DiscountInfo";
	}
	
	public static abstract class ImagesManagement implements BaseColumns{
		public static final String TABLE_NAME = "ImagesManagement";
		public static final String IMAGE_ID = "ImageId";
		public static final String ADVERTISER_ID = Advertisers.ADVERTISER_ID;
		public static final String IMAGE_LOCATION = "ImageLocation";
		public static final String IS_MAIN_PICTURE = "IsMainPicture";
	}

}
