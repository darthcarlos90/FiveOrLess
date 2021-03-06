package com.database;

import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.database.ContractClass;
import com.database.ContractClass.Advertisers;
import com.database.ContractClass.Dishes;

public class DatabaseHelper extends SQLiteOpenHelper {

	// If anything is changed on the database schema, this number must be
	// changed
	public static final int DATABASE_VERSION = 2;
	public static final String DATABASE_NAME = "FiveOrLessDb.db";

	// private static final String TAG = "FIVEXLESS";

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		RunCreates(db);
		InsertData(db);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		/*
		 * When the data of the table is upgraded, something must be done. In
		 * this case, the data will only be just deleted and the database will
		 * be created again.
		 */
		RunDeletes(db);
		onCreate(db);
	}

	@Override
	public void onOpen(SQLiteDatabase db) {
		super.onOpen(db);
	}

	private void RunCreates(SQLiteDatabase db) {
		db.execSQL(ContractClass.SQL_CREATE_ADVERTISERS);
		db.execSQL(ContractClass.SQL_CREATE_DISCOUNTS);
		db.execSQL(ContractClass.SQL_CREATE_DISHES);
	}

	/**
	 * Once the database has been created, it is time to start adding the
	 * information manually because prototype.
	 * 
	 * @param db
	 *            The database where are you adding the new data
	 */
	private void InsertData(SQLiteDatabase db) {
		// The values of the butterfinger elements
		insertIntoAdvertisersTable(db, "Butterfingers", "butterfingers",
				"Lunch", 0, "Butterfingers Sandwich Shop", 54.973248f,
				-1.6191699f, "59 St Andrew's St", "NE1 5SE");

		// The values of CoffeeShopAndSandwichBar
		insertIntoAdvertisersTable(db, "CoffeeShopAndSandwichBar",
				"coffeshopsandbar", "Lunch", 0, "Coffee Shop & Sandwich Bar",
				54.976217f, -1.618733f, "Leazes Park Road", "NE1 4PF");

		// The values of Frankie and Tonies
		insertIntoAdvertisersTable(db, "FrankieAndTonies", "ft", "Lunch", 0,
				"Frankie & Tony's", 54.97731f, -1.6120114f, "19 Ridley Pl",
				"NE1 8JN");

		// French Oven
		insertIntoAdvertisersTable(db, "FrenchOven", "fo", "Lunch", 0,
				"The French Oven Bakery", 54.972886f, -1.6149408f,
				"Grainger Market", "NE1 5QF");

		// Fez Food
		insertIntoAdvertisersTable(db, "FrezFood", "ff", "Lunch", 0,
				"Fez Food", 54.972886f, -1.6149408f, "Grainger Market",
				"NE1 5QF");

		// Grainger Pizza
		insertIntoAdvertisersTable(db, "GraingerPizza", "gpizza", "Lunch", 0,
				"Grainger Pizza", 54.972886f, -1.6149408f, "Grainger Market",
				"NE1 5QF");

		// Great Grub
		// TODO: No full info
		// insertIntoAdvertisersTable(db, "GreatGrub", "GreatGrub", "Lunch", 0,
		// "Great Grub");

		// Crepes
		insertIntoAdvertisersTable(db, "LePetitteCrepe", "lpc", "Snack", 0,
				"La Petite Creperie", 54.972886f, -1.6149408f,
				"Grainger Market", "NE1 5QF");

		// Pumphres
		insertIntoAdvertisersTable(db, "Pumphres", "pph", "Lunch", 0,
				"Pumphrey's Coffee Center", 54.972886f, -1.6149408f,
				"Grainger Market", "NE1 5QF");

		// QuilliamBrothers
		insertIntoAdvertisersTable(db, "QuilliamBrothers", "qb", "Tea time", 0,
				"Quilliam Brothers' Teahouse", 54.9793609f, -1.6130656f,
				"Claremont Buildings Eldon Square", "NE1 7RD");

		// Red Dumplings
		insertIntoAdvertisersTable(db, "RedDumpling", "rdumplings", "Lunch", 0,
				"Red Dumpling", 54.972886f, -1.6149408f, "Grainger Market",
				"NE1 5QF");

		// Shijo
		insertIntoAdvertisersTable(db, "Shijo", "shijo", "Lunch", 0, "Shijo",
				54.9774887f, -1.6138939f, "Northumberland St", "NE1 7QD");

		// Simply Seafood
		insertIntoAdvertisersTable(db, "SimplySeaFood", "ss", "Lunch", 0,
				"Simply Seafood", 54.972886f, -1.6149408f, "Grainger Market",
				"NE1 5QF");

		// SloppyJoes
		insertIntoAdvertisersTable(db, "SloppyJoes", "sj", "Lunch", 0,
				"Sloppy Joes", 54.972886f, -1.6149408f, "Grainger Market",
				"NE1 5QF");

		// The best Sandwich
		insertIntoAdvertisersTable(db, "TheBestSandwich", "tbs", "Lunch", 0,
				"The Best Sandwich", 54.9783619f, -1.6137698f, "Percy Street",
				"NE1 7RS");

		// WiFri
		insertIntoAdvertisersTable(db, "WiFri", "wf", "Lunch", 0, "Wi-Fri",
				54.972886f, -1.6149408f, "Grainger Market", "NE1 5QF");

		/*
		 * Now it is time to manually add the dishes ...
		 */
		insertIntoDishesTable(
				db,
				"Fish noodles",
				"Fresh food cooked right in front of you by the former personal chef of Portugese footballer Luis Figo.",
				"SimplySeaFood", 4.5);

		insertIntoDishesTable(
				db,
				"Chicken and Chorizo Baguette",
				"A great selection of freshly made sandiwches and paninis available at Sloppy Joe's in Grainger market",
				"SloppyJoes", 2.0);
		insertIntoDishesTable(
				db,
				"Meat Feast Sandwich",
				"An amazing meat feast sandwich, with succelent roast ham, prime beef and juicy chicken, topped with fresh salad",
				"FrankieAndTonies", 3.0);

	}

	/**
	 * Helper function to add into the database and not have to write ABSOLUTELY
	 * EVERYTHING again.
	 * 
	 * @param db
	 *            The database where we are inserting stuff.
	 * @param adv_name
	 *            The advertiser name.
	 * @param short_name
	 *            The short name of the advertiser.
	 * @param day_time
	 *            Day time of the advertiser.
	 * @param isFavorite
	 *            Is the advertiser on the favorites list?
	 * @param display_name
	 *            The display name of the advertiser
	 */
	private void insertIntoAdvertisersTable(SQLiteDatabase db, String adv_name,
			String short_name, String day_time, int isFavorite,
			String display_name, float x_pos, float y_pos, String address,
			String postcode) {

		ContentValues values = new ContentValues();
		values.put(Advertisers.ADVERTISER_NAME, adv_name);
		values.put(Advertisers.ADVERTISER_SHORT_NAME, short_name);
		values.put(Advertisers.DAY_TIME, day_time);
		values.put(Advertisers.IS_FAVORITE, isFavorite);
		values.put(Advertisers.DISPLAY_NAME, display_name);
		values.put(Advertisers.ADVERTISER_LONGITUDE, x_pos);
		values.put(Advertisers.ADVERTISER_LATITUDE, y_pos);
		values.put(Advertisers.ADVERTISER_ADDRESS, address);
		values.put(Advertisers.POSTCODE, postcode);
		db.insert(Advertisers.TABLE_NAME, null, values);
	}

	/**
	 * Inserts into the dish table.
	 * 
	 * @param db
	 *            The database to insert into
	 * @param dish_name
	 *            The name of the dish.
	 * @param dish_info
	 *            The information of the dish.
	 * @param advertiser_name
	 *            The name of the advertiser.
	 * @param dish_price
	 *            The price of the dish.
	 */
	private void insertIntoDishesTable(SQLiteDatabase db, String dish_name,
			String dish_info, String advertiser_name, double dish_price) {
		ContentValues values = new ContentValues();
		values.put(Dishes.DISH_NAME, dish_name);
		values.put(Dishes.DISH_INFO, dish_info);
		values.put(Dishes.DISH_PRICE, dish_price);
		String projection[] = { Advertisers.ADVERTISER_ID };
		Cursor c = db.query(Advertisers.TABLE_NAME, projection,
				Advertisers.ADVERTISER_NAME + " = '" + advertiser_name + "'",
				null, null, null, null, null);
		if (c.moveToFirst()) { // If the advertiser actually exists
			int id = c.getInt(0);
			c.close();
			values.put(Dishes.ADVERTISER_ID, id);
			db.insert(Dishes.TABLE_NAME, null, values);
		}

	}

	private void RunDeletes(SQLiteDatabase db) {
		db.execSQL(ContractClass.SQL_DELETE_ADVERTISERS);
		db.execSQL(ContractClass.SQL_DELETE_DISCOUNTS);
		db.execSQL(ContractClass.SQL_DELETE_DISHES);
	}

}
