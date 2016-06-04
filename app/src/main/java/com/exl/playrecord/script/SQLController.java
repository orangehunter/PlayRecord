package com.exl.playrecord.script;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.exl.playrecord.Struct.DB_datas;

import org.json.JSONObject;

import java.sql.Date;

public class SQLController {
    //public static final String KEY_ACTION = "action";
    //public static final String KEY_ACTIONID = "actionid";
    //public static final String KEY_SYSTEM = "system";
    //public static final String KEY_READED = "readed";

    DB_datas dbs;

	private static final String DATABASE_TABLE = "datas";
	private Context context;
	private SQLiteDatabase database;
	private SQLHelper dbHelper;

	public SQLController(Context context) {
		this.context = context;
	}

	public SQLController open() throws SQLException {
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

		return database.update(DATABASE_TABLE, updateValues, dbs.KEY_ROWID + "="
				+ rowId, null) > 0;
	}
	public boolean updateUpdateDate(long rowId,Date update) {
		ContentValues updateValues = new ContentValues();
		updateValues.put(dbs.KEY_UPDATE, update.toString());

		return database.update(DATABASE_TABLE, updateValues, dbs.KEY_ROWID + "="
				+ rowId, null) > 0;
	}

	public boolean deletesql(long rowId) {
		return database.delete(DATABASE_TABLE, dbs.KEY_ROWID + "=" + rowId, null) > 0;
	}

	public Cursor fetchAllsql() {
		return database.query(DATABASE_TABLE, new String[] {
                dbs.KEY_ROWID,
                dbs.KEY_TYPE,
                dbs.KEY_TITLE,
                dbs.KEY_SEASON,
                dbs.KEY_EPISODE,
                dbs.KEY_EPISODE_MAX,
                dbs.KEY_MESSAGE,
                dbs.KEY_CREATE,
                dbs.KEY_UPDATE,
                dbs.KEY_REMIND}, null, null, null,null, null);
	}

	public Cursor fetchsqlByID(long rowId) throws SQLException {
		Cursor mCursor = database.query(true, DATABASE_TABLE, new String[] {
                        dbs.KEY_ROWID,
                        dbs.KEY_TYPE,
                        dbs.KEY_TITLE,
                        dbs.KEY_SEASON,
                        dbs.KEY_EPISODE,
                        dbs.KEY_EPISODE_MAX,
                        dbs.KEY_MESSAGE,
                        dbs.KEY_CREATE,
                        dbs.KEY_UPDATE,
                        dbs.KEY_REMIND },
                dbs.KEY_ROWID + "=" + rowId, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	private ContentValues createContentValues(JSONObject json) {
		ContentValues value= new ContentValues();
		value.put(dbs.KEY_TYPE, json.optString(dbs.KEY_TYPE));
		value.put(dbs.KEY_TITLE, json.optString(dbs.KEY_TITLE));
        value.put(dbs.KEY_SEASON, json.optString(dbs.KEY_SEASON));
        value.put(dbs.KEY_EPISODE, json.optString(dbs.KEY_EPISODE));
        value.put(dbs.KEY_EPISODE_MAX, json.optString(dbs.KEY_EPISODE_MAX));
		value.put(dbs.KEY_MESSAGE, json.optString(dbs.KEY_MESSAGE));
        value.put(dbs.KEY_CREATE,json.optString(dbs.KEY_CREATE));
        value.put(dbs.KEY_UPDATE,json.optString(dbs.KEY_UPDATE));
        value.put(dbs.KEY_REMIND, json.optString(dbs.KEY_REMIND));
		return value;
	}

}
