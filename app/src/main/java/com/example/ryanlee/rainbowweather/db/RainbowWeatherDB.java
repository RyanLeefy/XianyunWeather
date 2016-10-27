package com.example.ryanlee.rainbowweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.ryanlee.rainbowweather.bean.City;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.schedulers.Schedulers;

/**
 * Created by Ryanlee on 2016/8/4 0004.
 */
public class RainbowWeatherDB {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "rainbow_weather";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    private static RainbowWeatherDB rainbowWeatherDB;

    private SQLiteDatabase db;

    private List<City> cities;

    private int limit = 50;   //每次加载条数

    private int offset;   //从第几条开始

    private String text; //搜索关键字





    /**
     * 将构造方法私有化
     */
    private RainbowWeatherDB(Context context){
        RainbowWeatherOpenHepler dbHepler = new RainbowWeatherOpenHepler(context, DB_NAME, null, VERSION);
        db = dbHepler.getWritableDatabase();

    }

    public synchronized static RainbowWeatherDB getInstance(Context context){   //单例
        if(rainbowWeatherDB == null){
            rainbowWeatherDB = new RainbowWeatherDB(context);
        }
        return rainbowWeatherDB;
    }


    /**
     * 将City实例存储到数据库
     */
    public void saveCity(City city){
        if(city != null){
            ContentValues values = new ContentValues();
            values.put("city_id", city.getCity_id());
            values.put("city_name", city.getCity_name());
            db.insert("city", null, values);
        }
    }


    /**
     * 从数据库读取城市信息
     */
    public List<City> loadCities(){
        List<City> list = new ArrayList<City>();
        //select  * from City   order by id  limit 5
        Cursor cursor = db.rawQuery("select  * from city order by id  limit 5",null);
        if(cursor.moveToFirst()){
            do{
                City city = new City();
                city.setCity_id(cursor.getString(cursor.getColumnIndex("city_id")));
                city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
                list.add(city);
            } while (cursor.moveToNext());
        }
        if(cursor != null){
            cursor.close();
        }
        return list;
    }


    /**
     * insertCity with Rxjava
     */
    public  Observable<Boolean> insertCity(List<City> cities){
        this.cities = cities;

        return Observable.create(new Observable.OnSubscribe<Boolean>(){
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                Log.d("DBcities:", String.valueOf(RainbowWeatherDB.this.cities.size()));
                for(int i = 0; i <  RainbowWeatherDB.this.cities.size(); i++) {
                    ContentValues values = new ContentValues();
                    values.put("city_id", RainbowWeatherDB.this.cities.get(i).getCity_id());
                    values.put("city_name", RainbowWeatherDB.this.cities.get(i).getCity_name());
                    db.insert("city", null, values);
                }
                subscriber.onNext(true);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());

    }



    /**
     * LoadCity with Rxjava
     */

    public  Observable<List<City>> LoadCity(int count){
        this.offset = this.limit * count;

        return Observable.create(new Observable.OnSubscribe<List<City>>(){
            @Override
            public void call(Subscriber<? super List<City>> subscriber) {
                List<City> list = new ArrayList<City>();
                Cursor cursor = db.rawQuery("select * from city order by id  limit 50 offset ?  ",new String[]{String.valueOf(RainbowWeatherDB.this.offset)});
                if(cursor.moveToFirst()){
                    do{
                        City city = new City();
                        city.setCity_id(cursor.getString(cursor.getColumnIndex("city_id")));
                        city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
                        list.add(city);
                    } while (cursor.moveToNext());
                }
                if(cursor != null){
                    cursor.close();
                }
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }


    /**
     * Search with Rxjava
     */
    public  Observable<List<City>> SearchCity(String text){
        this.text = text;
        return Observable.create(new Observable.OnSubscribe<List<City>>(){
            @Override
            public void call(Subscriber<? super List<City>> subscriber) {
                List<City> list = new ArrayList<City>();
                Cursor cursor = db.rawQuery("select * from city where city_name like ? order by id",new String[]{ "%" +RainbowWeatherDB.this.text + "%" });// order by id  limit 5
                if(cursor.moveToFirst()){
                    do{
                        City city = new City();
                        city.setCity_id(cursor.getString(cursor.getColumnIndex("city_id")));
                        city.setCity_name(cursor.getString(cursor.getColumnIndex("city_name")));
                        list.add(city);
                    } while (cursor.moveToNext());
                }
                if(cursor != null){
                    cursor.close();
                }
                subscriber.onNext(list);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io());
    }
}
