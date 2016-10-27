package com.example.ryanlee.rainbowweather.presenter;

import com.example.ryanlee.rainbowweather.bean.City;

/**
 * Created by Ryanlee on 2016/8/13 0013.
 */
public interface ICityPresenter {
    void onCreate();
    void onDestroy();
    void performOnClick(City city);
    void performOnClickforresult(City city);
    void performOnSearch(String text);
    void ShowCity(int count);
}
