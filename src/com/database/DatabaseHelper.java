package com.database;

import android.content.ContentValues;
import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.database.ContractClass;
import com.database.ContractClass.Advertisers;
import com.classes.*;

public class DatabaseHelper extends SQLiteOpenHelper {

	// If anything is changed on the database schema, this number must be
	// changed
	public static final int DATABASE_VERSION = 1;
	public static final String DATABASE_NAME = "FiveOrLessDb.db";

	public DatabaseHelper(Context context, String name, CursorFactory factory,
			int version) {
		super(context, name, factory, version);
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
		db.execSQL(ContractClass.SQL_CREATE_IMAGE);
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
		insertIntoAdvertisersTable(db, "Butterfingers", "Butterfingers",
				"Lunch", 0, "BUtterfingers Sandwich Shop");

		// The values of CoffeeShopAndSandwichBar
		insertIntoAdvertisersTable(db, "CoffeeShopAndSandwichBar",
				"CoffeShopSandBar", "Lunch", 0, "Coffee Shop And Sandwich Bar");

		// The values of Frankie and Tonies
		insertIntoAdvertisersTable(db, "FrankieAndTonies", "FT", "Lunch", 0,
				"Frankie & Tony's");

		// French Oven
		insertIntoAdvertisersTable(db, "FrenchOven", "FO", "Lunch", 0,
				"The French Oven");

		// Fez Food
		insertIntoAdvertisersTable(db, "FrezFood", "FF", "Lunch", 0, "Fez Food");

		// Grainger Pizza
		insertIntoAdvertisersTable(db, "GraingerPizza", "GPizza", "Lunch", 0,
				"Grainger Pizza");

		// Great Grub
		insertIntoAdvertisersTable(db, "GreatGrub", "GreatGrub", "Lunch", 0,
				"Great Grub");

		// Crepes
		insertIntoAdvertisersTable(db, "LePetitteCrepe", "LPC", "Snack", 0,
				"La Petite Creperie");

		// Pumphres
		insertIntoAdvertisersTable(db, "Pumphres", "PPH", "Lunch", 0,
				"Pumphres");

		// QuilliamBrothers
		insertIntoAdvertisersTable(db, "QuilliamBrothers", "QB", "Tea time", 0,
				"Quilliam Brothers");

		// Red Dumplings
		insertIntoAdvertisersTable(db, "RedDumpling", "RDumplings", "Lunch", 0,
				"Red Dumpling");

		// Shijo
		insertIntoAdvertisersTable(db, "Shijo", "Shijo", "Lunch", 0, "Shijo");

		// Simply Seafood
		insertIntoAdvertisersTable(db, "SimplySeaFood", "SS", "Lunch", 0,
				"Simply Seafood");

		// SloppyJoes
		insertIntoAdvertisersTable(db, "SloppyJoes", "SJ", "Lunch", 0,
				"Sloppy Joes");

		// The best Sandwich
		insertIntoAdvertisersTable(db, "TheBestSandwich", "TBS", "Lunch", 0,
				"The Best Sandwich");
		
		// WiFri
		insertIntoAdvertisersTable(db, "WiFri", "WF", "Lunch", 0, "Wi-Fri");

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
			String display_name) {

		ContentValues values = new ContentValues();
		values.put(Advertisers.ADVERTISER_NAME, adv_name);
		values.put(Advertisers.ADVERTISER_SHORT_NAME, short_name);
		values.put(Advertisers.DAY_TIME, day_time);
		values.put(Advertisers.IS_FAVORITE, isFavorite);
		values.put(Advertisers.DISPLAY_NAME, display_name);
		db.insert(Advertisers.TABLE_NAME, null, values);

	}

	private void RunDeletes(SQLiteDatabase db) {
		db.execSQL(ContractClass.SQL_DELETE_ADVERTISERS);
		db.execSQL(ContractClass.SQL_DELETE_DISCOUNTS);
		db.execSQL(ContractClass.SQL_DELETE_IMAGE);
	}

}
