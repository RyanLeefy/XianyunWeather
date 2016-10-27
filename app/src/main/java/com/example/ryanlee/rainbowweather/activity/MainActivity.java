package com.example.ryanlee.rainbowweather.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanlee.rainbowweather.R;
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
import java.util.Date;

import app.dinus.com.loadingdrawable.LoadingView;


public class MainActivity extends Activity implements IWeatherView {

    private TextView cityname,nowweek,nowdate,updatetime,nowcond,nowmintmp,nowmaxtmp,nowtmp,nowhum,nowwind,suggestion;
    private ImageView nowimg;
    private Daily_forecastLayout layout1,layout2,layout3,layout4,layout5;
    private IWeatherPresenter presenter;

    private City city = null;
    private LoadingView loadingview;
    private String updatetimestring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( getIntent().getSerializableExtra("city") != null){
            city = (City)getIntent().getSerializableExtra("city");
            //Log.d("MainActivityCity", city.getCity_name());
        }

        cityname = (TextView)findViewById(R.id.tv_cityname);
        cityname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this , ChooseCityActivity.class);
                startActivityForResult(i, 1);
            }
        });
        nowweek = (TextView)findViewById(R.id.tv_nowweek);
        nowdate = (TextView)findViewById(R.id.tv_nowdate);
        updatetime = (TextView)findViewById(R.id.tv_updatetime);
        nowimg = (ImageView)findViewById(R.id.img_now);
        nowcond = (TextView)findViewById(R.id.tv_nowcond);
        nowmintmp = (TextView)findViewById(R.id.tv_nowtmp_min);
        nowmaxtmp = (TextView)findViewById(R.id.tv_nowtmp_max);
        nowtmp = (TextView)findViewById(R.id.tv_nowtmp);
        nowhum = (TextView)findViewById(R.id.tv_nowhum);
        nowwind = (TextView)findViewById(R.id.tv_nowwind);
        suggestion = (TextView)findViewById(R.id.tv_suggestion);
        layout1 = (Daily_forecastLayout)findViewById(R.id.layout_day1);
        layout2 = (Daily_forecastLayout)findViewById(R.id.layout_day2);
        layout3 = (Daily_forecastLayout)findViewById(R.id.layout_day3);
        layout4 = (Daily_forecastLayout)findViewById(R.id.layout_day4);
        layout5 = (Daily_forecastLayout)findViewById(R.id.layout_day5);
        loadingview = (LoadingView)findViewById(R.id.level_view);

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        updatetimestring = sdf.format(curDate);

        presenter=new WeatherPresenter(this,city);
        presenter.onCreate();
    }

    @Override
    public void setData(WeatherResult weatherResult) {
        HeWeatherDataService30 heWeatherDataService30 = weatherResult.getHeWeatherDataService30().get(0);

        cityname.setText(heWeatherDataService30.getBasic().getCity());
        nowweek.setText(StringDateUtils.getInstance().toFormatStringWeek(heWeatherDataService30.getDailyForecast().get(0).getDate()));
        nowdate.setText(StringDateUtils.getInstance().toFormatStringDate(heWeatherDataService30.getDailyForecast().get(0).getDate()));
        updatetime.setText(updatetimestring);
        nowimg.setImageResource(ConPictureUtils.getInstance().getImageResourceByCondCode(heWeatherDataService30.getNow().getCond().getCode()));
        nowcond.setText(heWeatherDataService30.getNow().getCond().getTxt());
        nowmintmp.setText(heWeatherDataService30.getDailyForecast().get(0).getTmp().getMin());
        nowmaxtmp.setText(heWeatherDataService30.getDailyForecast().get(0).getTmp().getMax());
        nowtmp.setText(heWeatherDataService30.getNow().getTmp());
        nowhum.setText(heWeatherDataService30.getNow().getHum());
        nowwind.setText(heWeatherDataService30.getNow().getWind().getSc());
        if(heWeatherDataService30.getBasic().getCity().equals("南子岛")){
            suggestion.setText("");
        } else{
            suggestion.setText("        " + heWeatherDataService30.getSuggestion().getComf().getTxt());
        }
        layout1.setData(
                heWeatherDataService30.getDailyForecast().get(1).getDate(),
                heWeatherDataService30.getDailyForecast().get(1).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(1).getTmp().getMin(),
                heWeatherDataService30.getDailyForecast().get(1).getTmp().getMax(),
                heWeatherDataService30.getDailyForecast().get(1).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(1).getWind().getSc());

        layout2.setData(
                heWeatherDataService30.getDailyForecast().get(2).getDate(),
                heWeatherDataService30.getDailyForecast().get(2).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(2).getTmp().getMin(),
                heWeatherDataService30.getDailyForecast().get(2).getTmp().getMax(),
                heWeatherDataService30.getDailyForecast().get(2).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(2).getWind().getSc());

        layout3.setData(
                heWeatherDataService30.getDailyForecast().get(3).getDate(),
                heWeatherDataService30.getDailyForecast().get(3).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(3).getTmp().getMin(),
                heWeatherDataService30.getDailyForecast().get(3).getTmp().getMax(),
                heWeatherDataService30.getDailyForecast().get(3).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(3).getWind().getSc());

        layout4.setData(
                heWeatherDataService30.getDailyForecast().get(4).getDate(),
                heWeatherDataService30.getDailyForecast().get(4).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(4).getTmp().getMin(),
                heWeatherDataService30.getDailyForecast().get(4).getTmp().getMax(),
                heWeatherDataService30.getDailyForecast().get(4).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(4).getWind().getSc());

        layout5.setData(
                heWeatherDataService30.getDailyForecast().get(5).getDate(),
                heWeatherDataService30.getDailyForecast().get(5).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(5).getTmp().getMin(),
                heWeatherDataService30.getDailyForecast().get(5).getTmp().getMax(),
                heWeatherDataService30.getDailyForecast().get(5).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(5).getWind().getSc());

    }


    @Override
    public void setSharePreferenceData(){
        SharedPreferences sharedPreferences =  PreferenceManager.getDefaultSharedPreferences(this);

        cityname.setText(sharedPreferences.getString("city_name", null));
        nowweek.setText(sharedPreferences.getString("nowweek", null));
        nowdate.setText(sharedPreferences.getString("nowdate", null));
        updatetime.setText(sharedPreferences.getString("updatetime", null));
        nowimg.setImageResource(sharedPreferences.getInt("nowimg", 0));
        nowcond.setText(sharedPreferences.getString("nowcond", null));
        nowmintmp.setText(sharedPreferences.getString("nowmintmp", null));
        nowmaxtmp.setText(sharedPreferences.getString("nowmaxtmp", null));
        nowtmp.setText(sharedPreferences.getString("nowtmp", null));
        nowhum.setText(sharedPreferences.getString("nowhum", null));
        nowwind.setText(sharedPreferences.getString("nowwind", null));
        suggestion.setText(sharedPreferences.getString("suggestion", null));

        layout1.setShareperferenceData(sharedPreferences.getString("week1", null),
                sharedPreferences.getString("date1", null),
                sharedPreferences.getInt("img1", 0),
                sharedPreferences.getString("mintmp1", null),
                sharedPreferences.getString("maxtmp1", null),
                sharedPreferences.getString("cond1", null),
                sharedPreferences.getString("wind1", null)
        );

        layout2.setShareperferenceData(sharedPreferences.getString("week2", null),
                sharedPreferences.getString("date2", null),
                sharedPreferences.getInt("img2", 0),
                sharedPreferences.getString("mintmp2", null),
                sharedPreferences.getString("maxtmp2", null),
                sharedPreferences.getString("cond2", null),
                sharedPreferences.getString("wind2", null)
        );

        layout3.setShareperferenceData(sharedPreferences.getString("week3", null),
                sharedPreferences.getString("date3", null),
                sharedPreferences.getInt("img3", 0),
                sharedPreferences.getString("mintmp3", null),
                sharedPreferences.getString("maxtmp3", null),
                sharedPreferences.getString("cond3", null),
                sharedPreferences.getString("wind3", null)
        );

        layout4.setShareperferenceData(sharedPreferences.getString("week4", null),
                sharedPreferences.getString("date4", null),
                sharedPreferences.getInt("img4", 0),
                sharedPreferences.getString("mintmp4", null),
                sharedPreferences.getString("maxtmp4", null),
                sharedPreferences.getString("cond4", null),
                sharedPreferences.getString("wind4", null)
        );

        layout5.setShareperferenceData(sharedPreferences.getString("week5", null),
                sharedPreferences.getString("date5", null),
                sharedPreferences.getInt("img5", 0),
                sharedPreferences.getString("mintmp5", null),
                sharedPreferences.getString("maxtmp5", null),
                sharedPreferences.getString("cond5", null),
                sharedPreferences.getString("wind5", null)
        );
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

}
