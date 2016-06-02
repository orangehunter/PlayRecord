package com.exl.playrecord.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLHelper extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "ringline_data";
	private static final String CREATE_TABLE=
			"CREATE TABLE IF NOT EXISTS message("
					+ "_id INTEGER PRIMARY KEY,"
					+ "recive_date DATE,"
					+ "system TINYTEXT,"
					+ "type TINYTEXT,"
					+ "title TINYTEXT,"
					+ "message TEXT,"
					+ "action TINYTEXT,"
					+ "actionid TEXT,"
					+ "readed TINYINT[1],"
					+ "remind DATE)";
	
	public SQLHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL(CREATE_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
		Log.w(SQLHelper.class.getName(),"Upgrading database from version " + oldVersion + " to " + newVersion + ", which will destroy all old data");
        database.execSQL("DROP TABLE IF EXISTS message");  
        onCreate(database);
		
	}

}
