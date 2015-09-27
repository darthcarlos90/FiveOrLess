/**
 * The contract class is in charge of saving all the information of the
 * database, column names, even the SQL inserts and stuff maybe? MOre on that
 * later on....
 */

package com.database;

import android.provider.BaseColumns;

public abstract class ContractClass {
	public ContractClass() {
	}

	/*
	 * The base column interface is used so that the class comes with a _ID
	 * attribute that works on SOME Android data bases.
	 */

	// The inner classes that define the table contents
	public static abstract class Advertisers implements BaseColumns {
		public static final String TABLE_NAME = "Advertisers";
		public static final String ADVERTISER_ID = "AdvertiserId";
		public static final String ADVERTISER_NAME = "AdvertiserName";
		public static final String ADVERTISER_ADDRESS = "AdvertiserAddress";
		public static final String ADVERTISER_LONGITUDE = "AdvertiserLongitude";
		public static final String ADVERTISER_LATITUDE = "AdvertiserLatitude";
		public static final String ADVERTISER_INFO = "AdvertiserInfo";
		public static final String IS_FAVORITE = "IsFavorite";
		public static final String ADVERTISER_SHORT_NAME = "AdvertiserShortName";
		public static final String DAY_TIME = "DayTime";
		public static final String DISPLAY_NAME = "DisplayName";
		public static final String POSTCODE = "AdvertiserPostcode";

	}

	public static abstract class Discounts implements BaseColumns {
		public static final String TABLE_NAME = "Discounts";
		public static final String DISCOUNT_ID = "DiscountId";
		// Is this even correct? lol
		public static final String ADVERTISER_ID = Advertisers.ADVERTISER_ID;
		public static final String DISCOUNT_INFO = "DiscountInfo";
	}

	public static abstract class Dishes implements BaseColumns {
		public static final String TABLE_NAME = "Dishes";
		public static final String DISH_ID = "DishId";
		public static final String DISH_NAME = "DishName";
		public static final String ADVERTISER_ID = Advertisers.ADVERTISER_ID;
		public static final String DISH_INFO = "DishInfo";
		public static final String DISH_PRICE = "DishPrice";
	}

	// Now here come some helper constants to create the SQL statements
	private static final String TEXT_TYPE = " TEXT";
	private static final String INTEGER_TYPE = " INTEGER";
	private static final String REAL_TYPE = " REAL";
	private static final String COMMA_SEP = " , ";
	private static final String CREATE_TABLE = "CREATE TABLE ";
	private static final String OPEN_PARENTHESIS = " (";
	private static final String CLOSE_PARENTHESIS = " )";
	private static final String PRIMARY_KEY = " PRIMARY KEY";
	private static final String DROP_STATEMENT = "DROP TABLE IF EXISTS ";
	private static final String FOREIGN_KEY_1 = "FOREIGN KEY (";
	private static final String FOREIGN_KEY_2 = ") REFERENCES ";
	private static final String AUTO_INCREMENT = " AUTOINCREMENT";

	// Now the actual SQL statements
	public static final String SQL_CREATE_ADVERTISERS = CREATE_TABLE
			+ Advertisers.TABLE_NAME + OPEN_PARENTHESIS
			+ Advertisers.ADVERTISER_ID + INTEGER_TYPE + PRIMARY_KEY
			+ AUTO_INCREMENT + COMMA_SEP + Advertisers.ADVERTISER_NAME
			+ TEXT_TYPE + COMMA_SEP + Advertisers.ADVERTISER_ADDRESS
			+ TEXT_TYPE + COMMA_SEP + Advertisers.ADVERTISER_LONGITUDE
			+ REAL_TYPE + COMMA_SEP + Advertisers.ADVERTISER_LATITUDE
			+ REAL_TYPE + COMMA_SEP + Advertisers.ADVERTISER_INFO + TEXT_TYPE
			+ COMMA_SEP + Advertisers.IS_FAVORITE + INTEGER_TYPE + COMMA_SEP
			+ Advertisers.ADVERTISER_SHORT_NAME + TEXT_TYPE + COMMA_SEP
			+ Advertisers.DAY_TIME + TEXT_TYPE + COMMA_SEP
			+ Advertisers.DISPLAY_NAME + TEXT_TYPE + COMMA_SEP
			+ Advertisers.POSTCODE + TEXT_TYPE + CLOSE_PARENTHESIS;

	public static final String SQL_DELETE_ADVERTISERS = DROP_STATEMENT
			+ Advertisers.TABLE_NAME;

	public static final String SQL_CREATE_DISCOUNTS = CREATE_TABLE
			+ Discounts.TABLE_NAME + OPEN_PARENTHESIS + Discounts.DISCOUNT_ID
			+ INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP
			+ Discounts.DISCOUNT_INFO + TEXT_TYPE + COMMA_SEP
			+ Discounts.ADVERTISER_ID + INTEGER_TYPE + COMMA_SEP
			+ FOREIGN_KEY_1 + Discounts.ADVERTISER_ID + FOREIGN_KEY_2
			+ Advertisers.TABLE_NAME + OPEN_PARENTHESIS
			+ Advertisers.ADVERTISER_ID + CLOSE_PARENTHESIS + CLOSE_PARENTHESIS;

	public static final String SQL_DELETE_DISCOUNTS = DROP_STATEMENT
			+ Discounts.TABLE_NAME;

	public static final String SQL_CREATE_DISHES = CREATE_TABLE
			+ Dishes.TABLE_NAME + OPEN_PARENTHESIS + Dishes.DISH_ID
			+ INTEGER_TYPE + PRIMARY_KEY + AUTO_INCREMENT + COMMA_SEP
			+ Dishes.DISH_NAME + TEXT_TYPE + COMMA_SEP + Dishes.DISH_INFO
			+ TEXT_TYPE + COMMA_SEP + Dishes.DISH_PRICE + REAL_TYPE + COMMA_SEP
			+ Dishes.ADVERTISER_ID + INTEGER_TYPE + COMMA_SEP + FOREIGN_KEY_1
			+ Dishes.ADVERTISER_ID + FOREIGN_KEY_2 + Advertisers.TABLE_NAME
			+ OPEN_PARENTHESIS + Advertisers.ADVERTISER_ID + CLOSE_PARENTHESIS
			+ CLOSE_PARENTHESIS;

	public static final String SQL_DELETE_DISHES = DROP_STATEMENT
			+ Dishes.TABLE_NAME;
}
