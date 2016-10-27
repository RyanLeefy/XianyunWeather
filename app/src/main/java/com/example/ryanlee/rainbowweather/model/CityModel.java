package com.example.ryanlee.rainbowweather.model;

import com.example.ryanlee.rainbowweather.bean.CityResult;
import com.example.ryanlee.rainbowweather.http.HttpMethods;

import rx.Subscriber;

/**
 * Created by Ryanlee on 2016/8/4 0004.
 */
public class CityModel implements ICityModel {
    @Override
    public void getData(Subscriber<CityResult> subscriber) {
        HttpMethods.getInstance().getCity(subscriber, "allchina", "100766124e96473899d544d268c9730c");
    }
}
