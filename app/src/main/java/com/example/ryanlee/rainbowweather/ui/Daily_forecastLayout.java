package com.example.ryanlee.rainbowweather.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ryanlee.rainbowweather.R;
import com.example.ryanlee.rainbowweather.bean.Temperatur;
import com.example.ryanlee.rainbowweather.util.ConPictureUtils;
import com.example.ryanlee.rainbowweather.util.StringDateUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by Ryanlee on 2016/8/8 0008.
 */
public class Daily_forecastLayout extends RelativeLayout {

    private TextView tv_dayweek,tv_daydate,tv_day_morning_cond,tv_day_night_cond,tv_day_wind_power,tv_day_wind_direction;
    private ImageView img_day_morning_img,img_day_night_img;
    private WeatherLineView weatherLineView;
    private Daily_forecastLayout wlv_day;


    public Daily_forecastLayout(Context context,AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.daily_forecastlinearlayout, this, true);

        tv_dayweek = (TextView)findViewById(R.id.tv_dayweek);
        tv_daydate = (TextView)findViewById(R.id.tv_daydate);
        tv_day_morning_cond = (TextView)findViewById(R.id.tv_day_morning_cond);
        tv_day_night_cond = (TextView)findViewById(R.id.tv_day_night_cond);
        tv_day_wind_power = (TextView)findViewById(R.id.tv_day_wind_power);
        tv_day_wind_direction = (TextView)findViewById(R.id.tv_day_wind_direction);
        img_day_morning_img = (ImageView)findViewById(R.id.img_day_morning_img);
        img_day_night_img = (ImageView)findViewById(R.id.img_day_night_img);
        weatherLineView = (WeatherLineView)findViewById(R.id.wlv_day);

     }

    public void setData(String date, String morn_code ,String morn_cond,String night_code, String night_cond ,String wind_direction,String wind_power){
        this.tv_dayweek.setText(StringDateUtils.getInstance().toFormatStringWeek(date));
        this.tv_daydate.setText(StringDateUtils.getInstance().toFormatStringDate(date));
        this.img_day_morning_img.setImageResource(ConPictureUtils.getInstance().getImageResourceByCondCode(morn_code));
        this.tv_day_morning_cond.setText(morn_cond);
        this.img_day_night_img.setImageResource(ConPictureUtils.getInstance().getImageResourceByCondCode(night_code));
        this.tv_day_night_cond.setText(night_cond);

        if(!wind_power.equals("微风")){
            String format = getResources().getString(R.string.wind_power);
            wind_power= String.format(format, wind_power);
        }
        this.tv_day_wind_power.setText(wind_power);

        if(wind_direction.equals("无持续风向"))
            wind_direction = "无向";
        this.tv_day_wind_direction.setText(wind_direction);

    }

    //绘制折线
    public void setWeatherLineView(int position,String lowest,String highest,List<Temperatur> tmplist){
        //设置最大最小温度
        this.weatherLineView.setLowHighestData(Integer.valueOf(lowest),Integer.valueOf(highest));
        int[] low = new int[3];   //一个view上面有3点，左边，中间，右边
        int[] high = new int[3];  //一个view上面有3点
        low[1] = Integer.valueOf(tmplist.get(position).getMinTmp());
        high[1] = Integer.valueOf(tmplist.get(position).getMaxTmp());
        if(position<=0){
            low[0] = Integer.MIN_VALUE;
            high[0] = Integer.MAX_VALUE;
        } else {
            low[0] = (low[1] + Integer.valueOf(tmplist.get(position-1).getMinTmp())) / 2;
            high[0] = (high[1] + Integer.valueOf(tmplist.get(position-1).getMaxTmp())) / 2;
        }
        if(position>=tmplist.size()-1){
            low[2] = Integer.MIN_VALUE;
            high[2] = Integer.MAX_VALUE;
        } else {
            low[2] = (low[1] + Integer.valueOf(tmplist.get(position+1).getMinTmp())) / 2;
            high[2] = (high[1] + Integer.valueOf(tmplist.get(position+1).getMaxTmp())) / 2;
        }
        this.weatherLineView.setLowHighData(low, high);
    }

    public void setShareperferenceData(String formatweek, String formatdate ,int dayimgResource, String daycond, int nightimgResource, String nightcond, String winddirection, String windpower){
        this.tv_dayweek.setText(formatweek);
        this.tv_daydate.setText(formatdate);
        this.img_day_morning_img.setImageResource(dayimgResource);
        this.tv_day_morning_cond.setText(daycond);
        this.img_day_night_img.setImageResource(nightimgResource);
        this.tv_day_night_cond.setText(nightcond);

        if(!windpower.equals("微风")){
            String format = getResources().getString(R.string.wind_power);
            windpower= String.format(format, windpower);
        }
        this.tv_day_wind_power.setText(windpower);

        if(winddirection.equals("无持续风向"))
            winddirection = "无向";
        this.tv_day_wind_direction.setText(winddirection);
    }

}

