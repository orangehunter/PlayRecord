package com.exl.playrecord.script;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.exl.playrecord.Struct.Item_names;

public class SQLHelper extends SQLiteOpenHelper {

    static Item_names dbs;

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "play_record_data";
    private static final String DATABASE_TABLE = "datas";
    private static final String CREATE_TABLE=
            "CREATE TABLE IF NOT EXISTS "+DATABASE_TABLE+"("
                    +dbs.KEY_ROWID+" INTEGER PRIMARY KEY,"
                    +dbs.KEY_TYPE+" TINYTEXT,"
                    +dbs.KEY_TITLE+" TINYTEXT,"
                    +dbs.KEY_SEASON+" TINYINT[1],"
                    +dbs.KEY_SEASON_EPISODES+" TEXT,"
                    +dbs.KEY_MESSAGE+" TEXT,"
                    +dbs.KEY_CREATE+" Long,"
                    +dbs.KEY_UPDATE+" Long,"
                    +dbs.KEY_REMIND+ " Long)";

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
