package com.example.ryanlee.rainbowweather.http;

import com.example.ryanlee.rainbowweather.bean.WeatherResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ryanlee on 2016/8/5 0005.
 */
public interface WeatherService {
    @GET("weather")
    Observable<WeatherResult> getWeather(@Query("cityid") String cityid, @Query("key") String key);
}
