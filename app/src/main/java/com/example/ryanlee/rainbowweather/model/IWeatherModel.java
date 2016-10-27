package com.example.ryanlee.rainbowweather.model;

import com.example.ryanlee.rainbowweather.bean.WeatherResult;

import rx.Subscriber;

/**
 * Created by Ryanlee on 2016/8/4 0004.
 */
public interface IWeatherModel {
    void getData(Subscriber<WeatherResult> subscriber,String citycode);
}
