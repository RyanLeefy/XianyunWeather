package com.example.ryanlee.rainbowweather.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryanlee on 2016/8/7 0007.
 */
public class WeatherResult {

    @SerializedName("HeWeather data service 3.0")
    @Expose
    private List<HeWeatherDataService30> heWeatherDataService30 = new ArrayList<HeWeatherDataService30>();

    /**
     *
     * @return
     * The heWeatherDataService30
     */
    public List<HeWeatherDataService30> getHeWeatherDataService30() {
        return heWeatherDataService30;
    }

    /**
     *
     * @param heWeatherDataService30
     * The HeWeather data service 3.0
     */
    public void setHeWeatherDataService30(List<HeWeatherDataService30> heWeatherDataService30) {
        this.heWeatherDataService30 = heWeatherDataService30;
    }

}
