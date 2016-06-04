package com.exl.playrecord.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.exl.playrecord.Struct.DB_datas;

public class SQLHelper extends SQLiteOpenHelper {

    static DB_datas dbs;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "play_record_data";
    private static final String CREATE_TABLE=
            "CREATE TABLE IF NOT EXISTS message("
                    +dbs.KEY_ROWID+" INTEGER PRIMARY KEY,"
                    +dbs.KEY_TYPE+" TINYTEXT,"
                    +dbs.KEY_TITLE+" TINYTEXT,"
                    +dbs.KEY_SEASON+" TINYINT[1],"
                    +dbs.KEY_EPISODE+" TINYINT[1],"
                    +dbs.KEY_EPISODE_MAX+" TINYINT[1],"
                    +dbs.KEY_MESSAGE+" TINYTEXT,"
                    +dbs.KEY_CREATE+" DATE,"
                    +dbs.KEY_UPDATE+" DATE,"
                    +dbs.KEY_REMIND+ " DATE)";

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
