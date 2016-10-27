package com.example.ryanlee.rainbowweather.http;

import com.example.ryanlee.rainbowweather.bean.CityResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Ryanlee on 2016/8/5 0005.
 */
public interface CityService {
    @GET("citylist")
    Observable<CityResult> getCity(@Query("search") String search, @Query("key") String key);

}
