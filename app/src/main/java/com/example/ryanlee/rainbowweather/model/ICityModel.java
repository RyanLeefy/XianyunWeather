package com.example.ryanlee.rainbowweather.model;

import com.example.ryanlee.rainbowweather.bean.CityResult;

import rx.Subscriber;

/**
 * Created by Ryanlee on 2016/8/4 0004.
 */
public interface ICityModel {
    void getData(Subscriber<CityResult> subscriber);
}
