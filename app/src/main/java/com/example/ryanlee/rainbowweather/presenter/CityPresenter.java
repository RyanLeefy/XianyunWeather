package com.example.ryanlee.rainbowweather.presenter;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.ryanlee.rainbowweather.activity.ChooseCityActivity;
import com.example.ryanlee.rainbowweather.activity.MainActivity;
import com.example.ryanlee.rainbowweather.adapter.CityAdapter;
import com.example.ryanlee.rainbowweather.bean.City;
import com.example.ryanlee.rainbowweather.bean.CityResult;
import com.example.ryanlee.rainbowweather.db.RainbowWeatherDB;
import com.example.ryanlee.rainbowweather.model.CityModel;
import com.example.ryanlee.rainbowweather.model.ICityModel;
import com.example.ryanlee.rainbowweather.util.MyApplication;
import com.example.ryanlee.rainbowweather.util.MyCompositeSubscription;
import com.example.ryanlee.rainbowweather.view.ICityView;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by Ryanlee on 2016/8/13 0013.
 */
public class CityPresenter implements ICityPresenter {

    private ICityView view;
    private ICityModel model;

    private Boolean HasDownLoad;   //选择了城市没
    private int count;  //跳过多少页（跳过多少50city）
    private Subscriber<CityResult> subscriber;
    private ArrayList<City> cities;
    private SharedPreferences perPreferences;
    protected CompositeSubscription mSubscriptions = new CompositeSubscription();

    //presenter构造函数
    public CityPresenter(ICityView view){
        this.view = view;
        model = new CityModel();
    }

    @Override
    public void onCreate() {

        mSubscriptions = MyCompositeSubscription.getNewCompositeSubIfUnsubscribed(mSubscriptions);

        perPreferences = MyApplication.getContext().getSharedPreferences("HasDownLoad", MyApplication.getContext().MODE_PRIVATE);

        subscriber = new Subscriber<CityResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                Toast.makeText((ChooseCityActivity)view, "获取数据失败", Toast.LENGTH_LONG).show();
                //Log.d("ChooseCityActivity", "!!Error");
            }

            @Override
            public void onNext(CityResult cityResult) {
                //Log.d("ChooseCityActivity", "success");
                if(cityResult.getStatus().equals("ok")){     //获取数据成功,存入数据库中
                    cities = new ArrayList<City>();
                    for(int i = 0 ; i <cityResult.getCityInfo().size() ; i++){
                        City c = new City();
                        c.setCity_id(cityResult.getCityInfo().get(i).getId());
                        c.setCity_name(cityResult.getCityInfo().get(i).getCity());
                        cities.add(c);
                        //Log.d("ChooseCityActivity", "111");
                    }
                    mSubscriptions.add(RainbowWeatherDB.getInstance((ChooseCityActivity) view)
                            .insertCity(cities)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new Subscriber<Boolean>() {
                                @Override
                                public void onCompleted() {

                                }

                                @Override
                                public void onError(Throwable e) {
                                    e.printStackTrace();
                                    Toast.makeText((ChooseCityActivity)view, "获取数据失败", Toast.LENGTH_LONG).show();
                                    //Log.d("ChooseCityActivity", "4");
                                }

                                @Override
                                public void onNext(Boolean aBoolean) {    //存入数据库完成
                                    //Log.d("ChooseCityActivity", "5");
                                    if (aBoolean == true) {
                                        //Log.d("ChooseCityActivity", "6");
                                        //存入数据库完成后，读取List进行显示
                                        Toast.makeText(MyApplication.getContext(), "存入数据库", Toast.LENGTH_LONG).show();
                                        ShowCity(0);
                                        SharedPreferences.Editor editor = CityPresenter.this.perPreferences.edit();
                                        editor.putBoolean("HasDownLoad", true);
                                        editor.commit();
                                    }
                                }
                            }));

                } else {
                    //Log.d("ChooseCityActivity", "7");
                    Toast.makeText((ChooseCityActivity)view, "获取数据失败", Toast.LENGTH_LONG).show();
                }
            }
        };

        //判断下载了没
        HasDownLoad = perPreferences.getBoolean("HasDownLoad",false);
        if (!HasDownLoad) {
            model.getData(subscriber);  //首次登陆，下载城市数据，存入数据库，读取前50条显示
        }else{
            ShowCity(0);                //选择城市，读取前50条显示
        }

    }

    @Override
    public void performOnClick(City city) {
        Activity chooseactivity = (ChooseCityActivity)view;
        Intent i = new Intent( chooseactivity , MainActivity.class );
        i.putExtra("city",city);
        chooseactivity.startActivity(i);
        chooseactivity.finish();
    }

    @Override
    public void performOnClickforresult(City city){
        Activity chooseactivity = (ChooseCityActivity)view;
        Intent i = new Intent();
        i.putExtra("city", city);
        chooseactivity.setResult(Activity.RESULT_OK, i);
        chooseactivity.finish();
    }

    @Override
    public void performOnSearch(String text) {
        mSubscriptions.add(RainbowWeatherDB.getInstance(MyApplication.getContext())
                .SearchCity(text)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<City>>() {
                    @Override
                    public void onCompleted() {
                        //Log.d("ChooseCityActivity", "1");
                    }

                    @Override
                    public void onError(Throwable e) {
                        //Log.d("ChooseCityActivity", "2");
                        e.printStackTrace();
                        Toast.makeText((ChooseCityActivity)view, "搜索失败", Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onNext(List<City> cities) {    //把查询结果更新到ui上
                        //Log.d("cities.size():", String.valueOf(cities.size()));
                        if (cities.size() == 0) {
                            //Log.d("ChooseCityActivity", "3");
                            Toast.makeText((ChooseCityActivity) view, "查询不到相关结果", Toast.LENGTH_LONG).show();
                        } else {
                            CityAdapter adapter = new CityAdapter((ChooseCityActivity) view, cities);
                            view.setAdapter(adapter);
                        }
                    }
                }));
    }

    @Override
    public void ShowCity(int count){
        this.count = count;
        mSubscriptions.add(RainbowWeatherDB.getInstance(MyApplication.getContext())
                .LoadCity(count)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<City>>() {
                    @Override
                    public void onCompleted() {
                        //Log.d("ChooseCityActivity", "1");
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        Toast.makeText((ChooseCityActivity) view, "读取城市列表失败", Toast.LENGTH_LONG).show();
                        //Log.d("ChooseCityActivity", "2");
                    }

                    @Override
                    public void onNext(List<City> cities) {
                        //Log.d("ChooseCityActivity", "3");
                        if (CityPresenter.this.count == 0) {   //显示前50条
                            CityAdapter adapter = new CityAdapter((ChooseCityActivity) view, cities);
                            view.setAdapter(adapter);
                        } else {                               //滚动到底部，加载多50条并隐藏loadingLayout
                            view.hideLoadingLayout();
                            view.updateView(cities);         //把新的cities，addall到原有的list当中更
                        }
                    }
                }));
    }

    @Override
    public void onDestroy(){
        mSubscriptions = MyCompositeSubscription.getNewCompositeSubIfUnsubscribed(mSubscriptions);
        mSubscriptions.unsubscribe();
    }


}
