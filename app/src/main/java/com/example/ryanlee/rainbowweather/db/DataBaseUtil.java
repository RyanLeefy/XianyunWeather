package com.example.ryanlee.rainbowweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import com.example.ryanlee.rainbowweather.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 * Created by Ryanlee on 2016/10/31 0031.
 */
public class DataBaseUtil {
    private Context context;
    private static String dbName = "rainbow_weather.db";
    private static String DATABASE_PATH;

    public DataBaseUtil(Context context){
        this.context = context;
        String packagename = context.getPackageName();
        //DATABASE_PATH = "/data/data/" + packagename + "/databases/";
        DATABASE_PATH = "/data/data/" + packagename + "/databases/";
    }



    /**
     * 复制数据库到手机指定文件夹下
     * @throws IOException
     */
    public void copyDataBase(){

        File dir = new File(DATABASE_PATH);
        if(!dir.exists())
            dir.mkdir();
        

        if (!(new File(DATABASE_PATH + dbName)).exists()) {
            // 如 SQLite 数据库文件不存在，再检查一下 database 目录是否存在
        String databaseFilename = DATABASE_PATH + dbName;
        //File dir = new File(databaseFilename);
        //if(!dir.exists())
        //    dir.mkdir();
        try{
            FileOutputStream os = new FileOutputStream(databaseFilename);

            InputStream is = context.getResources().openRawResource(R.raw.rainbow_weather);

            byte[] buffer = new byte[8192];
            int count = 0;

            while ((count = is.read(buffer)) > 0) {
                os.write(buffer, 0, count);
            }
            os.flush();
            is.close();
            os.close();

        } catch (Exception ex){
            ex.printStackTrace();
        }

        }

    }


}
