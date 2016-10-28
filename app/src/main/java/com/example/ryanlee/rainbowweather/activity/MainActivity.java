package com.example.ryanlee.rainbowweather.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanlee.rainbowweather.R;
import com.example.ryanlee.rainbowweather.adapter.MyPagerAdapter;
import com.example.ryanlee.rainbowweather.bean.City;
import com.example.ryanlee.rainbowweather.bean.HeWeatherDataService30;
import com.example.ryanlee.rainbowweather.bean.WeatherResult;
import com.example.ryanlee.rainbowweather.presenter.IWeatherPresenter;
import com.example.ryanlee.rainbowweather.presenter.WeatherPresenter;
import com.example.ryanlee.rainbowweather.ui.Daily_forecastLayout;
import com.example.ryanlee.rainbowweather.util.ConPictureUtils;
import com.example.ryanlee.rainbowweather.util.StringDateUtils;
import com.example.ryanlee.rainbowweather.view.IWeatherView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import app.dinus.com.loadingdrawable.LoadingView;
import fr.castorflex.android.verticalviewpager.VerticalViewPager;

public class MainActivity extends FragmentActivity implements IWeatherView,ForecastWeatherFragment.OnFragmentInteractionListener,NowWeatherFragment.OnFragmentInteractionListener {

    public static int SETPREFERFLAG = 0;

    private TextView cityname;
    private VerticalViewPager viewPager;
    private NowWeatherFragment nowfragment;
    private ForecastWeatherFragment forecastfragment;
    private MyPagerAdapter pagerAdapter;
    private IWeatherPresenter presenter;

    private City city = null;
    private LoadingView loadingview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( getIntent().getSerializableExtra("city") != null){
            city = (City)getIntent().getSerializableExtra("city");
        }

        cityname = (TextView)findViewById(R.id.tv_cityname);
        cityname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ChooseCityActivity.class);
                startActivityForResult(i, 1);
            }
        });

        viewPager = (VerticalViewPager)findViewById(R.id.vp_vertical);
        nowfragment = new NowWeatherFragment();
        forecastfragment = new ForecastWeatherFragment();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(nowfragment);
        fragmentList.add(forecastfragment);

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager() , fragmentList);
        viewPager.setAdapter(pagerAdapter);

        loadingview = (LoadingView)findViewById(R.id.level_view);

        presenter=new WeatherPresenter(this,city);
        presenter.onCreate();

    }

    @Override
    public void onStart(){
        super.onStart();

    }

    @Override
    public void setData(WeatherResult weatherResult) {
        HeWeatherDataService30 heWeatherDataService30 = weatherResult.getHeWeatherDataService30().get(0);

        cityname.setText(heWeatherDataService30.getBasic().getCity());

        nowfragment.setData(weatherResult);
        forecastfragment.setData(weatherResult);
        pagerAdapter.notifyDataSetChanged();

    }


    @Override
    public void setSharePreferenceData(){
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);
        cityname.setText(sharedPreferences.getString("city_name", null));

        //把设置数据的操作放到fragment的OnResume()里面，若FLAG是1则设置数据。
        SETPREFERFLAG = 1;

    }


    @Override
    public void setLoadingViewVisibility(int Visibility){
        if(Visibility == View.VISIBLE){
            loadingview.setVisibility(View.VISIBLE);
        } else{
            loadingview.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        switch (requestCode){
            case 1:
                if(resultCode == RESULT_OK){
                    City newcity = (City)data.getSerializableExtra("city");
                    presenter=new WeatherPresenter(this,newcity);
                    presenter.onCreate();
                }
        }
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
