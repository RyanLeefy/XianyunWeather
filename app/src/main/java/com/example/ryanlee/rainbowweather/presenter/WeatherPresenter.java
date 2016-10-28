package com.example.ryanlee.rainbowweather.presenter;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.ryanlee.rainbowweather.R;
import com.example.ryanlee.rainbowweather.activity.MainActivity;
import com.example.ryanlee.rainbowweather.bean.City;
import com.example.ryanlee.rainbowweather.bean.HeWeatherDataService30;
import com.example.ryanlee.rainbowweather.bean.WeatherResult;
import com.example.ryanlee.rainbowweather.model.IWeatherModel;
import com.example.ryanlee.rainbowweather.model.WeatherModel;
import com.example.ryanlee.rainbowweather.util.ConPictureUtils;
import com.example.ryanlee.rainbowweather.util.MyApplication;
import com.example.ryanlee.rainbowweather.util.MyCompositeSubscription;
import com.example.ryanlee.rainbowweather.util.StringDateUtils;
import com.example.ryanlee.rainbowweather.view.IWeatherView;

import java.text.SimpleDateFormat;
import java.util.Date;

import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ryanlee on 2016/8/9 0009.
 */
public class WeatherPresenter implements IWeatherPresenter {

    private IWeatherView view;
    private IWeatherModel model;
    Subscriber<WeatherResult> subscriber;
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    City city;

    public WeatherPresenter(IWeatherView view,City city){
        this.view=view;
        model=new WeatherModel();
        this.city = city;
    }

    @Override
    public void onCreate() {

        subscriber = new Subscriber<WeatherResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                //Log.d("MainActivitylog", "!!Error");
            }

            @Override
            public void onNext(WeatherResult weatherResult) {
                HeWeatherDataService30 heWeatherDataService30 = weatherResult.getHeWeatherDataService30().get(0);

                if(heWeatherDataService30.getStatus().equals("ok")){   //获取数据成功，显示数据，并保存数据
                    view.setData(weatherResult);
                    SaveDataInSharePreference(weatherResult);
                    view.setLoadingViewVisibility(View.INVISIBLE); //读取成功后取消Loading画面
                    Toast.makeText((MainActivity)view, "更新天气成功", Toast.LENGTH_SHORT).show();
                } else{                               //获取失败
                    Toast.makeText((MainActivity)view, "获取天气失败！", Toast.LENGTH_LONG).show();
                }

            }
        };

        SharedPreferences perPreferences = PreferenceManager.getDefaultSharedPreferences((Context) view);
        Boolean IshaveSharePreferences = perPreferences.getBoolean("IshaveSharePreferences" , false);
        if (IshaveSharePreferences) {
            //有sharedpreferences ,先显示数据
            if(city != null && city.getCity_id() != perPreferences.getString("city_id",null)){
                //view.setLoadingViewVisibility(View.VISIBLE);
                model.getData(subscriber, city.getCity_id());
            } else {
                view.setSharePreferenceData();
                model.getData(subscriber, perPreferences.getString("city_id",null));
            }
        }else{
            //没有存对应的sharedpreferences，显示Loading画面
            //view.setLoadingViewVisibility(View.VISIBLE);
            model.getData(subscriber, city.getCity_id());
        }

    }

    public void SaveDataInSharePreference(WeatherResult weatherResult){

        HeWeatherDataService30 heWeatherDataService30 = weatherResult.getHeWeatherDataService30().get(0);

        //获取当前时间
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String updatetimestring = sdf.format(curDate);

        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext()).edit();

        editor.putBoolean("IshaveSharePreferences", true).commit();
        editor.putString("city_name", heWeatherDataService30.getBasic().getCity())
                .putString("city_id", heWeatherDataService30.getBasic().getId())
                .commit();

        editor.putString("api",heWeatherDataService30.getAqi().getCity().getAqi())
                .putString("nowweek", StringDateUtils.getInstance().toFormatStringWeek(heWeatherDataService30.getDailyForecast().get(0).getDate()))
                .putString("updatetime", updatetimestring)
                .putString("nowtmp", heWeatherDataService30.getNow().getTmp())
                .putString("nowcond", heWeatherDataService30.getNow().getCond().getTxt())
                .putString("nowwind", heWeatherDataService30.getNow().getWind().getSc())
                .putString("nowhumidity", heWeatherDataService30.getNow().getHum())
                .putString("nowziwaixian", heWeatherDataService30.getSuggestion().getUv().getBrf())
                .putString("nowqiya", heWeatherDataService30.getNow().getPres())
                .putInt("nowimg", ConPictureUtils.getInstance().getImageResourceByCondCode(heWeatherDataService30.getNow().getCond().getCode()))
                .commit();


        for(int i=0; i<= 5; i++){
             editor .putString("week" + i, StringDateUtils.getInstance().toFormatStringWeek(heWeatherDataService30.getDailyForecast().get(i).getDate()))
                    .putString("date" + i, StringDateUtils.getInstance().toFormatStringDate(heWeatherDataService30.getDailyForecast().get(i).getDate()))
                    .putInt("day_img" + i, ConPictureUtils.getInstance().getImageResourceByCondCode(heWeatherDataService30.getDailyForecast().get(i).getCond().getCodeD()))
                     .putString("day_cond" + i, heWeatherDataService30.getDailyForecast().get(i).getCond().getTxtD())
                     .putInt("night_img" + i, ConPictureUtils.getInstance().getImageResourceByCondCode(heWeatherDataService30.getDailyForecast().get(i).getCond().getCodeN()))
                     .putString("night_cond" + i, heWeatherDataService30.getDailyForecast().get(i).getCond().getTxtN())
                     .putString("wind_direction" + i, heWeatherDataService30.getDailyForecast().get(i).getWind().getDir())
                     .putString("wind_power" + i, heWeatherDataService30.getDailyForecast().get(i).getWind().getSc())
                     .commit();
        }


    }

    @Override
    public void onDestroy(){
        mSubscriptions = MyCompositeSubscription.getNewCompositeSubIfUnsubscribed(mSubscriptions);
        mSubscriptions.unsubscribe();
    }


}
