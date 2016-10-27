package com.example.ryanlee.rainbowweather.http;

import com.example.ryanlee.rainbowweather.bean.CityResult;
import com.example.ryanlee.rainbowweather.bean.WeatherResult;
import com.example.ryanlee.rainbowweather.util.MyCompositeSubscription;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ryanlee on 2016/8/5 0005.
 */
public class HttpMethods {

    public static final String BASE_URL =  "https://api.heweather.com/x3/";

    private static final int DEFAULT_TIMEOUT = 5;

    //创建HttpMethods单例
    private static HttpMethods httpmethods = new HttpMethods();

    private Retrofit retrofit;

    private CityService cityService;

    private WeatherService weatherService;

    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    /**
     * 构造方法私有
     */
    public HttpMethods(){
        //手动创建一个OkHttpClient并设置超时时间
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(httpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();

        cityService = retrofit.create(CityService.class);

        weatherService = retrofit.create(WeatherService.class);

    }

    public static HttpMethods getInstance(){
        return httpmethods;
    }


    /**
     * 获取城市列表
     * @param subscriber 由调用者传过来的观察者对象
     * @param search 类型
     * @param key 认证key
     */
    public void getCity(Subscriber<CityResult> subscriber, String search, String key){
        mSubscriptions = MyCompositeSubscription.getNewCompositeSubIfUnsubscribed(mSubscriptions);
        mSubscriptions.add(cityService.getCity(search, key)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }


    /**
     * 获取城市列表
     * @param subscriber 由调用者传过来的观察者对象
     * @param cityid 城市代码
     * @param key 认证key
     */
    public void getWeather(Subscriber<WeatherResult> subscriber, String cityid, String key){
        mSubscriptions = MyCompositeSubscription.getNewCompositeSubIfUnsubscribed(mSubscriptions);
        mSubscriptions.add(weatherService.getWeather(cityid, key)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }




}
