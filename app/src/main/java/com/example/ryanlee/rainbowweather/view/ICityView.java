package com.example.ryanlee.rainbowweather.view;

import com.example.ryanlee.rainbowweather.adapter.CityAdapter;
import com.example.ryanlee.rainbowweather.bean.City;

import java.util.List;

/**
 * Created by Ryanlee on 2016/8/4 0004.
 */
public interface ICityView {
    void updateView(List<City> cities);
    void setAdapter(CityAdapter adapter);
    void hideLoadingLayout();
    void setLoadingViewVisibility(int Visibility);
}
