package com.example.ryanlee.rainbowweather.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ryanlee.rainbowweather.R;
import com.example.ryanlee.rainbowweather.bean.HeWeatherDataService30;
import com.example.ryanlee.rainbowweather.bean.Temperatur;
import com.example.ryanlee.rainbowweather.bean.WeatherResult;
import com.example.ryanlee.rainbowweather.ui.Daily_forecastLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ForecastWeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ForecastWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForecastWeatherFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private Daily_forecastLayout layout1,layout2,layout3,layout4,layout5,layout6;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";



    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForecastWeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ForecastWeatherFragment newInstance(String param1, String param2) {
        ForecastWeatherFragment fragment = new ForecastWeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public ForecastWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_forecast_weather, container, false);

        layout1 = (Daily_forecastLayout)v.findViewById(R.id.layout_forest1);
        layout2 = (Daily_forecastLayout)v.findViewById(R.id.layout_forest2);
        layout3 = (Daily_forecastLayout)v.findViewById(R.id.layout_forest3);
        layout4 = (Daily_forecastLayout)v.findViewById(R.id.layout_forest4);
        layout5 = (Daily_forecastLayout)v.findViewById(R.id.layout_forest5);
        layout6 = (Daily_forecastLayout)v.findViewById(R.id.layout_forest6);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction();
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        if(MainActivity.SETPREFERFLAG==1)
            setSharePreferenceData();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
         void onFragmentInteraction();
    }

    public void setData(WeatherResult weatherResult){
        HeWeatherDataService30 heWeatherDataService30 = weatherResult.getHeWeatherDataService30().get(0);


        //把每一天的最大最小温度取出来存入tmp数据中，再把tmp数据存入tmplist中
        List<Temperatur> tmplist = new ArrayList<Temperatur>();
        List<Temperatur> list = new ArrayList<Temperatur>();
        for(int i=0;i<=5;i++){
            Temperatur tmp = new Temperatur();
            Temperatur fuzhutmp = new Temperatur();
            tmp.setMaxTmp(heWeatherDataService30.getDailyForecast().get(i).getTmp().getMax());
            tmp.setMinTmp(heWeatherDataService30.getDailyForecast().get(i).getTmp().getMin());
            fuzhutmp.setMaxTmp(heWeatherDataService30.getDailyForecast().get(i).getTmp().getMax());
            fuzhutmp.setMinTmp(heWeatherDataService30.getDailyForecast().get(i).getTmp().getMin());
            tmplist.add(tmp);
            list.add(fuzhutmp);
        }



        layout1.setData(
                "今天",
                heWeatherDataService30.getDailyForecast().get(0).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(0).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(0).getCond().getCodeN(),
                heWeatherDataService30.getDailyForecast().get(0).getCond().getTxtN(),
                heWeatherDataService30.getDailyForecast().get(0).getWind().getDir(),
                heWeatherDataService30.getDailyForecast().get(0).getWind().getSc());

        layout2.setData(
                heWeatherDataService30.getDailyForecast().get(1).getDate(),
                heWeatherDataService30.getDailyForecast().get(1).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(1).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(1).getCond().getCodeN(),
                heWeatherDataService30.getDailyForecast().get(1).getCond().getTxtN(),
                heWeatherDataService30.getDailyForecast().get(1).getWind().getDir(),
                heWeatherDataService30.getDailyForecast().get(1).getWind().getSc());

        layout3.setData(
                heWeatherDataService30.getDailyForecast().get(2).getDate(),
                heWeatherDataService30.getDailyForecast().get(2).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(2).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(2).getCond().getCodeN(),
                heWeatherDataService30.getDailyForecast().get(2).getCond().getTxtN(),
                heWeatherDataService30.getDailyForecast().get(2).getWind().getDir(),
                heWeatherDataService30.getDailyForecast().get(2).getWind().getSc());

        layout4.setData(
                heWeatherDataService30.getDailyForecast().get(3).getDate(),
                heWeatherDataService30.getDailyForecast().get(3).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(3).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(3).getCond().getCodeN(),
                heWeatherDataService30.getDailyForecast().get(3).getCond().getTxtN(),
                heWeatherDataService30.getDailyForecast().get(3).getWind().getDir(),
                heWeatherDataService30.getDailyForecast().get(3).getWind().getSc());

        layout5.setData(
                heWeatherDataService30.getDailyForecast().get(4).getDate(),
                heWeatherDataService30.getDailyForecast().get(4).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(4).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(4).getCond().getCodeN(),
                heWeatherDataService30.getDailyForecast().get(4).getCond().getTxtN(),
                heWeatherDataService30.getDailyForecast().get(4).getWind().getDir(),
                heWeatherDataService30.getDailyForecast().get(4).getWind().getSc());

        layout6.setData(
                heWeatherDataService30.getDailyForecast().get(5).getDate(),
                heWeatherDataService30.getDailyForecast().get(5).getCond().getCodeD(),
                heWeatherDataService30.getDailyForecast().get(5).getCond().getTxtD(),
                heWeatherDataService30.getDailyForecast().get(5).getCond().getCodeN(),
                heWeatherDataService30.getDailyForecast().get(5).getCond().getTxtN(),
                heWeatherDataService30.getDailyForecast().get(5).getWind().getDir(),
                heWeatherDataService30.getDailyForecast().get(5).getWind().getSc());


        //画出折线图
        //利用list找出最大最小温度
        Collections.sort(list, new Comparator<Temperatur>() {
            @Override
            public int compare(Temperatur lhs,
                               Temperatur rhs) {
                // 排序找到温度最低的，按照最低温度升序排列
                return Integer.valueOf(lhs.getMinTmp()) - Integer.valueOf(rhs.getMinTmp());
            }
        });
        String lowest = list.get(0).getMinTmp();

        Collections.sort(list, new Comparator<Temperatur>() {
            @Override
            public int compare(Temperatur lhs,
                               Temperatur rhs) {
                // 排序找到温度最低的，按照最低温度升序排列
                return Integer.valueOf(rhs.getMaxTmp()) - Integer.valueOf(lhs.getMaxTmp());
            }
        });
        String highest = list.get(0).getMaxTmp();


        layout1.setWeatherLineView(0,lowest,highest,tmplist);
        layout2.setWeatherLineView(1,lowest,highest,tmplist);
        layout3.setWeatherLineView(2,lowest,highest,tmplist);
        layout4.setWeatherLineView(3,lowest,highest,tmplist);
        layout5.setWeatherLineView(4,lowest,highest,tmplist);
        layout6.setWeatherLineView(5,lowest,highest,tmplist);

    }

    public void setSharePreferenceData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        layout1.setShareperferenceData(sharedPreferences.getString("week0", null),
                sharedPreferences.getString("date0", null),
                sharedPreferences.getInt("day_img0", 0),
                sharedPreferences.getString("day_cond0", null),
                sharedPreferences.getInt("night_img0", 0),
                sharedPreferences.getString("night_cond0", null),
                sharedPreferences.getString("wind_direction0", null),
                sharedPreferences.getString("wind_power0", null)
        );

        layout2.setShareperferenceData(sharedPreferences.getString("week1", null),
                sharedPreferences.getString("date1", null),
                sharedPreferences.getInt("day_img1", 0),
                sharedPreferences.getString("day_cond1", null),
                sharedPreferences.getInt("night_img1", 0),
                sharedPreferences.getString("night_cond1", null),
                sharedPreferences.getString("wind_direction1", null),
                sharedPreferences.getString("wind_power1", null)
        );


        layout3.setShareperferenceData(sharedPreferences.getString("week2", null),
                sharedPreferences.getString("date2", null),
                sharedPreferences.getInt("day_img2", 0),
                sharedPreferences.getString("day_cond2", null),
                sharedPreferences.getInt("night_img2", 0),
                sharedPreferences.getString("night_cond2", null),
                sharedPreferences.getString("wind_direction2", null),
                sharedPreferences.getString("wind_power2", null)
        );


        layout4.setShareperferenceData(sharedPreferences.getString("week3", null),
                sharedPreferences.getString("date3", null),
                sharedPreferences.getInt("day_img3", 0),
                sharedPreferences.getString("day_cond3", null),
                sharedPreferences.getInt("night_img3", 0),
                sharedPreferences.getString("night_cond3", null),
                sharedPreferences.getString("wind_direction3", null),
                sharedPreferences.getString("wind_power3", null)
        );


        layout5.setShareperferenceData(sharedPreferences.getString("week4", null),
                sharedPreferences.getString("date4", null),
                sharedPreferences.getInt("day_img4", 0),
                sharedPreferences.getString("day_cond4", null),
                sharedPreferences.getInt("night_img4", 0),
                sharedPreferences.getString("night_cond4", null),
                sharedPreferences.getString("wind_direction4", null),
                sharedPreferences.getString("wind_power4", null)
        );


        layout6.setShareperferenceData(sharedPreferences.getString("week5", null),
                sharedPreferences.getString("date5", null),
                sharedPreferences.getInt("day_img5", 0),
                sharedPreferences.getString("day_cond5", null),
                sharedPreferences.getInt("night_img5", 0),
                sharedPreferences.getString("night_cond5", null),
                sharedPreferences.getString("wind_direction5", null),
                sharedPreferences.getString("wind_power5", null)
        );



    }
}
