package com.exl.playrecord.script;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.exl.playrecord.Constant;

/**
 * Created by Soldat on 2016/5/29.
 */
public class ControlDatabase extends SQLiteOpenHelper {

    final private static int _DB_VERSION = 1;
    final private static String _DB_DATABASE_NAME = "PlayRecord.db";

    public ControlDatabase(Context context, SQLiteDatabase.CursorFactory factory) {
        super(context, _DB_DATABASE_NAME, factory, _DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
