package com.exl.playrecord.script;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.util.SparseArray;

import com.exl.playrecord.Struct.Item_datas;
import com.exl.playrecord.Struct.Item_names;
import com.exl.playrecord.script.SeasonEpisoates.DeCode;
import com.exl.playrecord.script.SeasonEpisoates.EnCode;

public class SQLController {

    Item_names dbs;

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

	public long insertsql( Item_datas data) {
		ContentValues initialValues = createContentValues(data);
		return database.insert(DATABASE_TABLE, null, initialValues);
	}

	public boolean updatesql(Item_datas data) {
		ContentValues updateValues = createContentValues(data);

		return database.update(DATABASE_TABLE, updateValues, dbs.KEY_ROWID + "="
				+ data.id, null) > 0;
	}
	/*public boolean updateUpdateDate(long rowId,Date update) {
		ContentValues updateValues = new ContentValues();
		updateValues.put(dbs.KEY_UPDATE, update.toString());

		return database.update(DATABASE_TABLE, updateValues, dbs.KEY_ROWID + "="
				+ rowId, null) > 0;
	}*/

	public boolean deletesql(Item_datas data) {
		return database.delete(DATABASE_TABLE, dbs.KEY_ROWID + "=" + data.id, null) > 0;
	}

	public Cursor fetchAllsql() {
		return database.query(DATABASE_TABLE, new String[] {
                dbs.KEY_ROWID,
                dbs.KEY_TYPE,
                dbs.KEY_TITLE,
				dbs.KEY_SEASON,
                dbs.KEY_SEASON_EPISODES,
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
                        dbs.KEY_SEASON_EPISODES,
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

	private ContentValues createContentValues(Item_datas datas) {
		ContentValues value= new ContentValues();
		value.put(dbs.KEY_TYPE,datas.type);
		value.put(dbs.KEY_TITLE,datas.title);
		value.put(dbs.KEY_SEASON,datas.season);

		EnCode seE=new EnCode(datas);
        value.put(dbs.KEY_SEASON_EPISODES,seE.toString());

		value.put(dbs.KEY_MESSAGE,datas.message);
        value.put(dbs.KEY_CREATE,datas.create_date);
        value.put(dbs.KEY_UPDATE,datas.update_date);
        value.put(dbs.KEY_REMIND,datas.remind);
		return value;
	}
	public SparseArray<Item_datas> renewDatasFromSQL(){
		Cursor cursor=fetchAllsql();
		SparseArray<Item_datas> tmp=new SparseArray<Item_datas>();
		try{
			while (cursor.moveToNext()){
				Item_datas item = null;
				item.id             =cursor.getLong(0);
				item.type           =cursor.getString(1);
				item.title          =cursor.getString(2);
				item.season         =cursor.getInt(3);
				DeCode sed=new DeCode(cursor.getString(4));
				item.seasonMax      =sed.getSize();
				item=sed.getEpisode_EpisoateMax(item);
				item.message        =cursor.getString(5);
				item.create_date    =cursor.getInt(6);
				item.update_date    =cursor.getInt(7);
				item.remind         =cursor.getInt(8);

				item.isCustomSeason=sed.isCustomSeason();
				if (item.isCustomSeason){
					item.customSeasonNames=sed.getCustomSeason();
				}else {
					item.customSeasonNames=null;
				}
			}
		}finally {
			cursor.close();
		}
		if (tmp.size()==0){
			Log.e("Class Variable","Cursor is null");
		}
		return tmp;
	}
}
