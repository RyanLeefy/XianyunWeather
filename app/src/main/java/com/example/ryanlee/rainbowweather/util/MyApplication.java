package com.example.ryanlee.rainbowweather.util;

import android.app.Application;
import android.content.Context;

/**
 * Created by Ryanlee on 2016/8/15 0015.
 */
public class MyApplication extends Application {
    private static Context context;
    private static int FLAG_CHOSE_ISFORE = 0;
    private static int FLAG_MAIN_ISFORE = 0;

    @Override
    public void onCreate(){
        context = getApplicationContext();
    }

    public static Context getContext(){
        return context;
    }

    public static void setCHOSEFlagIsfore(int i){
        FLAG_CHOSE_ISFORE = i;
    }

    public static int getCHOSEFlagIsfore(){
        return FLAG_CHOSE_ISFORE;
    }

    public static void setMAINFlagIsfore(int i){
        FLAG_MAIN_ISFORE = i;
    }

    public static int getMAINFlagIsfore(){
        return FLAG_MAIN_ISFORE;
    }
}
