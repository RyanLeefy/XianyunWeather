package com.example.ryanlee.rainbowweather.model;

import com.example.ryanlee.rainbowweather.bean.WeatherResult;
import com.example.ryanlee.rainbowweather.http.HttpMethods;

import rx.Subscriber;

/**
 * Created by Ryanlee on 2016/8/4 0004.
 */
public class WeatherModel implements IWeatherModel {
    @Override
    public void getData(Subscriber<WeatherResult> subscriber , String citycode) {
        HttpMethods.getInstance().getWeather(subscriber, citycode , "100766124e96473899d544d268c9730c");
    }
}
