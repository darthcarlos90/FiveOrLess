package com.database;

import android.content.Context;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.database.ContractClass;

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
		// TODO Auto-generated method stub
		super.onOpen(db);
	}

	private void RunCreates(SQLiteDatabase db) {
		db.execSQL(ContractClass.SQL_CREATE_ADVERTISERS);
		db.execSQL(ContractClass.SQL_CREATE_DISCOUNTS);
		db.execSQL(ContractClass.SQL_CREATE_IMAGE);
	}

	private void RunDeletes(SQLiteDatabase db) {
		db.execSQL(ContractClass.SQL_DELETE_ADVERTISERS);
		db.execSQL(ContractClass.SQL_DELETE_DISCOUNTS);
		db.execSQL(ContractClass.SQL_DELETE_IMAGE);
	}

}
