package com.example.ryanlee.rainbowweather.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ryanlee.rainbowweather.R;
import com.example.ryanlee.rainbowweather.util.ConPictureUtils;
import com.example.ryanlee.rainbowweather.util.StringDateUtils;

import java.util.Date;

/**
 * Created by Ryanlee on 2016/8/8 0008.
 */
public class Daily_forecastLayout extends RelativeLayout {
    private TextView week;
    private TextView date;
    private ImageView img;
    private TextView mintmp;
    private TextView maxtmp;
    private TextView cond;
    private TextView wind;

    public Daily_forecastLayout(Context context,AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.daily_forecastlinearlayout, this, true);

        week = (TextView)findViewById(R.id.tv_dayweek);
        date = (TextView)findViewById(R.id.tv_daydate);
        img = (ImageView)findViewById(R.id.img_day);
        mintmp = (TextView)findViewById(R.id.tv_daytmp_min);
        maxtmp = (TextView)findViewById(R.id.tv_daytmp_max);
        cond = (TextView)findViewById(R.id.tv_daycond);
        wind = (TextView)findViewById(R.id.tv_daywind);

     }

    public void setData(String date, String code_d ,String mintmp,String maxtmp, String cond ,String wind){
        this.week.setText(StringDateUtils.getInstance().toFormatStringWeek(date));
        this.date.setText(StringDateUtils.getInstance().toFormatStringDate(date));
        this.img.setImageResource(ConPictureUtils.getInstance().getImageResourceByCondCode(code_d));
        this.mintmp.setText(mintmp);
        this.maxtmp.setText(maxtmp);
        this.cond.setText(cond);
        this.wind.setText(wind);
    }

    public void setShareperferenceData(String formatweek, String formatdate ,int imgResource, String mintmp,String maxtmp, String cond, String wind){
        this.week.setText(formatweek);
        this.date.setText(formatdate);
        this.img.setImageResource(imgResource);
        this.mintmp.setText(mintmp);
        this.maxtmp.setText(maxtmp);
        this.cond.setText(cond);
        this.wind.setText(wind);
    }

}

