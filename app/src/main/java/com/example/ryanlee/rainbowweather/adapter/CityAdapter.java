package com.example.ryanlee.rainbowweather.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ryanlee.rainbowweather.R;
import com.example.ryanlee.rainbowweather.bean.City;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryanlee on 2016/8/13 0013.
 */
public class CityAdapter extends BaseAdapter {

    private List<City> cities = new ArrayList<City>();
    private LayoutInflater mInflater;

    public CityAdapter(Context context, List<City> cities ){
        this.cities = cities;
        this.mInflater = LayoutInflater.from(context);

    }
    public void updateAdapter(List<City> cities){
        this.cities.addAll(cities);
    }

    @Override
    public int getCount() {
        return cities.size();
    }

    @Override
    public City getItem(int position) {
        return cities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ItemViewTag viewTag;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_list_layout,null);

            viewTag = new ItemViewTag((TextView)convertView.findViewById(R.id.tv_cityname));
            convertView.setTag(viewTag);
        } else {
            viewTag = (ItemViewTag)convertView.getTag();
        }

        viewTag.tv_cityname.setText(cities.get(position).getCity_name());

        return convertView;
    }



    class ItemViewTag {
        protected TextView tv_cityname;

        public ItemViewTag(TextView tv_cityname){
            super();
            this.tv_cityname = tv_cityname;
        }
    }


}
