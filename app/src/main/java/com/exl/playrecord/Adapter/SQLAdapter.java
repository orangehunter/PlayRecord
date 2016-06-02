package com.exl.playrecord.Adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.json.JSONObject;

public class SQLAdapter {
	public static final String KEY_ROWID = "_id";

	public static final String KEY_SYSTEM = "system";
	public static final String KEY_TYPE = "type";
	public static final String KEY_TITLE = "title";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_ACTION = "action";
	public static final String KEY_ACTIONID = "actionid";

	public static final String KEY_RECIVE = "recive_date";
	public static final String KEY_READED = "readed";
	public static final String KEY_REMIND = "remind";

	private static final String DATABASE_TABLE = "message";
	private Context context;
	private SQLiteDatabase database;
	private SQLHelper dbHelper;

	public SQLAdapter(Context context) {
		this.context = context;  
	}  

	public SQLAdapter open() throws SQLException {
		dbHelper = new SQLHelper(context);  
		database = dbHelper.getWritableDatabase();  
		return this;  
	}  

	public void close() {  
		dbHelper.close();  
	}  

	public long insertsql(JSONObject json) {
		ContentValues initialValues = createContentValues(json);

		return database.insert(DATABASE_TABLE, null, initialValues);  
	}
	
	 public boolean updatesql(long rowId,JSONObject json) {
			ContentValues updateValues = createContentValues(json);
	  
	        return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "="  
	                + rowId, null) > 0;  
	    } 
	 public boolean updateReaded(long rowId,int readed) {  
			ContentValues updateValues = new ContentValues();
			updateValues.put(KEY_READED, readed);
	  
	        return database.update(DATABASE_TABLE, updateValues, KEY_ROWID + "="  
	                + rowId, null) > 0;  
	    }
	 
	 public boolean deletesql(long rowId) {  
	        return database.delete(DATABASE_TABLE, KEY_ROWID + "=" + rowId, null) > 0;  
	    }
	 
	 public Cursor fetchAllsql() {
	        return database.query(DATABASE_TABLE, new String[] {
	        		KEY_ROWID,
	        		KEY_RECIVE ,
	        		KEY_READED,
	        		KEY_REMIND,
	        		KEY_SYSTEM,
	        		KEY_TYPE,
	        		KEY_TITLE,
	        		KEY_MESSAGE,
	        		KEY_ACTION,
	        		KEY_ACTIONID}, null, null, null,null, null);  
	    }  
	 
	 public Cursor fetchsqlByID(long rowId) throws SQLException {
	        Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
	        		KEY_ROWID,
	        		KEY_RECIVE ,
	        		KEY_READED,
	        		KEY_REMIND,
	        		KEY_SYSTEM,
	        		KEY_TYPE,
	        		KEY_TITLE,
	        		KEY_MESSAGE,
	        		KEY_ACTION,
	        		KEY_ACTIONID },  
	                KEY_ROWID + "=" + rowId, null, null, null, null, null);  
	        if (mCursor != null) {  
	            mCursor.moveToFirst();  
	        }  
	        return mCursor;  
	    }  

	private ContentValues createContentValues(JSONObject json) {
		ContentValues value= new ContentValues();
		value.put(KEY_RECIVE, json.optString(KEY_RECIVE));
		value.put(KEY_READED, json.optInt(KEY_READED));
		value.put(KEY_REMIND, json.optString(KEY_REMIND));

		value.put(KEY_SYSTEM, json.optString(KEY_SYSTEM));
		value.put(KEY_TYPE, json.optString(KEY_TYPE));
		value.put(KEY_TITLE, json.optString(KEY_TITLE));
		value.put(KEY_MESSAGE, json.optString(KEY_MESSAGE));
		value.put(KEY_ACTION, json.optString(KEY_ACTION));
		value.put(KEY_ACTIONID, json.optString(KEY_ACTIONID));
		return value;  
	}  

}
