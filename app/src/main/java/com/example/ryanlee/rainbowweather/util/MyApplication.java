package com.example.ryanlee.rainbowweather.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ryanlee on 2016/8/15 0015.
 */
public class MyApplication extends Application {
    private static Context context;
    private static int FLAG_ISFORE = 0;

    @Override
    public void onCreate(){
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

    public static void setFlagIsfore(int i){
        FLAG_ISFORE = i;
    }

    public static int getFlagIsfore(){
        return FLAG_ISFORE;
    }
}
