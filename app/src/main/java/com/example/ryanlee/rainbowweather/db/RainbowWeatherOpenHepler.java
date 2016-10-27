package com.example.ryanlee.rainbowweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Ryanlee on 2016/8/4 0004.
 */
public class RainbowWeatherOpenHepler extends SQLiteOpenHelper {

    /**
     * city表建表语句
     */
    public static final String CREATE_CITY = "create table city("
            + "id integer primary key autoincrement, "
            + "city_id text, "
            + "city_name text)";


    public RainbowWeatherOpenHepler(Context context, String name, CursorFactory factory, int version){
        super(context,name,factory,version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CITY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
