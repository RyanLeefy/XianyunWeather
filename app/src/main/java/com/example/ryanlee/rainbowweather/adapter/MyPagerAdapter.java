package com.example.ryanlee.rainbowweather.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.ViewGroup;

import com.example.ryanlee.rainbowweather.activity.ForecastWeatherFragment;
import com.example.ryanlee.rainbowweather.activity.NowWeatherFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryanlee on 2016/10/28 0028.
 */
public class MyPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list = new ArrayList<Fragment>();

    public MyPagerAdapter(FragmentManager fm){
        super(fm);
    }

    public MyPagerAdapter(FragmentManager fm,List<Fragment> list){
        super(fm);
        this.list = list;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }


    @Override
    public int getCount() {
        return list.size();
    }
}
