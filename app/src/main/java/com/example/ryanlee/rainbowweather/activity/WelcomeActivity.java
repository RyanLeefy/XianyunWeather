package com.example.ryanlee.rainbowweather.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

/**
 * Created by Ryanlee on 2016/8/15 0015.
 */
public class WelcomeActivity extends Activity {

    private boolean isCitySelect = false;

    private static final int TIME = 2*1000;
    private static final int TO_MAIN = 100001;
    private static final int TO_CHOOSE = 100002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }


    Handler myHandler = new Handler(){
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case TO_MAIN:
                    Intent i1 = new Intent(WelcomeActivity.this,MainActivity.class);
                    startActivity(i1);
                    finish();
                    //Intent i1 = new Intent(WelcomeActivity.this,ChooseCityActivity.class);
                    //startActivity(i1);
                    //finish();
                    break;
                case TO_CHOOSE:
                    Intent i2 = new Intent(WelcomeActivity.this,ChooseCityActivity.class);
                    startActivity(i2);
                    finish();
                    break;
            }
        };
    };


    private void init() {
        //将用户是否是第一次使用的值用SharedPreferences存储到本地
        SharedPreferences perPreferences = getSharedPreferences("isCitySelect", MODE_PRIVATE);
        isCitySelect = perPreferences.getBoolean("isCitySelect", false);
        if (isCitySelect) {
            myHandler.sendEmptyMessage(TO_MAIN);
        }else{
            myHandler.sendEmptyMessage(TO_CHOOSE);
        }

    }
}
