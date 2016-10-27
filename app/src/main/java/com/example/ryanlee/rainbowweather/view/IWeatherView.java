package com.example.ryanlee.rainbowweather.view;

import com.example.ryanlee.rainbowweather.bean.WeatherResult;

/**
 * Created by Ryanlee on 2016/8/4 0004.
 */
public interface IWeatherView {
    void setData(WeatherResult weatherResult);
    void setSharePreferenceData();
    void setLoadingViewVisibility(int Visibility);
}
