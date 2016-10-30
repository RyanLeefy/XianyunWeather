package com.example.ryanlee.rainbowweather.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ryanlee.rainbowweather.R;
import com.example.ryanlee.rainbowweather.adapter.MyPagerAdapter;
import com.example.ryanlee.rainbowweather.bean.City;
import com.example.ryanlee.rainbowweather.bean.HeWeatherDataService30;
import com.example.ryanlee.rainbowweather.bean.WeatherResult;
import com.example.ryanlee.rainbowweather.presenter.IWeatherPresenter;
import com.example.ryanlee.rainbowweather.presenter.WeatherPresenter;
import com.example.ryanlee.rainbowweather.util.MyApplication;
import com.example.ryanlee.rainbowweather.view.IWeatherView;

import java.util.ArrayList;
import java.util.List;

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
    private RelativeLayout loadinglayout;

    private SwipeRefreshLayout swipeRefreshLayout;
    private Handler mHandler = new Handler();

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
                Intent i = new Intent(MainActivity.this, ChooseCityActivity.class);
                startActivityForResult(i, 1);
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swiperefreshlayout);

        swipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        // 刷新动画开始后回调到此方法

                        mHandler.postDelayed(new Runnable() {
                            public void run() {
                                //if(!=null)
                                presenter.onCreate();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        }, 2000);
                    }
                }
        );

        viewPager = (VerticalViewPager)findViewById(R.id.vp_vertical);
        nowfragment = new NowWeatherFragment();
        forecastfragment = new ForecastWeatherFragment();
        List<Fragment> fragmentList = new ArrayList<Fragment>();
        fragmentList.add(nowfragment);
        fragmentList.add(forecastfragment);

        pagerAdapter = new MyPagerAdapter(getSupportFragmentManager() , fragmentList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if(position==0)
                    swipeRefreshLayout.setEnabled(true);     //第一页时候可用，可下拉
                else if(position==1)
                    swipeRefreshLayout.setEnabled(false);    //第二页时候不可用
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        loadinglayout = (RelativeLayout)findViewById(R.id.loadinglayout);

        presenter=new WeatherPresenter(this,city);
        presenter.onCreate();

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
            loadinglayout.setVisibility(View.VISIBLE);
        } else{
            loadinglayout.setVisibility(View.INVISIBLE);
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

    //在onStart()中设置前台FLAG，以防activity从home通过onstart()回来。
    @Override
    protected void onStart(){
        super.onStart();
        MyApplication.setFlagIsfore(1);
    }

    @Override
    protected void onStop(){
        super.onStop();
        //按Home键弹出去时候，把切换到后台的信息传给presenter
        presenter.setback();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

    @Override
    public void onFragmentInteraction() {
        presenter.onCreate();
    }

}


