package com.example.ryanlee.rainbowweather.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ryanlee.rainbowweather.R;
import com.example.ryanlee.rainbowweather.bean.HeWeatherDataService30;
import com.example.ryanlee.rainbowweather.bean.WeatherResult;
import com.example.ryanlee.rainbowweather.util.ConPictureUtils;
import com.example.ryanlee.rainbowweather.util.MyApplication;
import com.example.ryanlee.rainbowweather.util.StringDateUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NowWeatherFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NowWeatherFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NowWeatherFragment extends Fragment {

    private TextView tv_api,tv_nowweek,tv_updatetime,tv_nowtmp,
            tv_nowcond,tv_nowwind,tv_nowhumidity,tv_nowziwaixian,tv_nowqiya;

    private ImageView img_nowcond;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
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
     * @return A new instance of fragment NowWeatherFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NowWeatherFragment newInstance(String param1, String param2) {
        NowWeatherFragment fragment = new NowWeatherFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public NowWeatherFragment() {
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
        View v =  inflater.inflate(R.layout.fragment_now_weather, container, false);
        tv_api          = (TextView)v.findViewById(R.id.tv_api);
        tv_nowweek      = (TextView)v.findViewById(R.id.tv_nowweek);
        tv_updatetime   = (TextView)v.findViewById(R.id.tv_updatetime);
        tv_nowtmp       = (TextView)v.findViewById(R.id.tv_nowtmp);
        tv_nowcond      = (TextView)v.findViewById(R.id.tv_nowcond);
        tv_nowwind      = (TextView)v.findViewById(R.id.tv_nowwind);
        tv_nowhumidity  = (TextView)v.findViewById(R.id.tv_nowhumidity);
        tv_nowziwaixian = (TextView)v.findViewById(R.id.tv_nowziwaixian);
        tv_nowqiya      = (TextView)v.findViewById(R.id.tv_nowqiya);
        img_nowcond     = (ImageView)v.findViewById(R.id.img_nowcond);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
        public void onFragmentInteraction(Uri uri);
    }

    public void setData(WeatherResult weatherResult){
        HeWeatherDataService30 heWeatherDataService30 = weatherResult.getHeWeatherDataService30().get(0);

        String api = heWeatherDataService30.getAqi().getCity().getAqi();

        int api_int = Integer.parseInt(api);
        GradientDrawable mygrad = (GradientDrawable) tv_api.getBackground();
        if(api_int<=50) {
            mygrad.setColor(getResources().getColor(R.color.green));//优
            tv_api.setText("优");
        } else if(api_int<=100){
            mygrad.setColor(getResources().getColor(R.color.liang));//良
            tv_api.setText("良");
        } else if(api_int<=150){
            mygrad.setColor(getResources().getColor(R.color.qingdu));//轻度
            tv_api.setText("轻度");
        } else if(api_int<=200){
            mygrad.setColor(getResources().getColor(R.color.zhongdu1));//中度
            tv_api.setText("中度");
        } else if(api_int<=300){
            mygrad.setColor(getResources().getColor(R.color.zhongdu2));//重度
            tv_api.setText("重度");
        } else if(api_int<=500){
            mygrad.setColor(getResources().getColor(R.color.yanzhong));//严重
            tv_api.setText("严重");
        }

        tv_nowweek.setText(StringDateUtils.getInstance().toFormatStringWeek(heWeatherDataService30.getDailyForecast().get(0).getDate()));

        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date curDate = new Date(System.currentTimeMillis());
        String updatetimestring = sdf.format(curDate);
        String format = getResources().getString(R.string.refreshtime);
        String updatetime= String.format(format, updatetimestring);
        tv_updatetime.setText(updatetime);


        format = getResources().getString(R.string.temperature);
        String temperature = String.format(format,heWeatherDataService30.getNow().getTmp());
        tv_nowtmp.setText(temperature);

        tv_nowcond.setText(heWeatherDataService30.getNow().getCond().getTxt());

        String windstring = heWeatherDataService30.getNow().getWind().getSc();
        if(!windstring.equals("微风")){
            format = getResources().getString(R.string.wind_power);
            windstring= String.format(format ,windstring);
        }
        tv_nowwind.setText(windstring);

        String humiditystring = heWeatherDataService30.getNow().getHum();
        format = getResources().getString(R.string.humidity);
        humiditystring = String.format(format, humiditystring);
        tv_nowhumidity.setText(humiditystring);

        tv_nowziwaixian.setText(heWeatherDataService30.getSuggestion().getUv().getBrf());

        String qiyastring = heWeatherDataService30.getNow().getPres();
        format = getResources().getString(R.string.qiya);
        qiyastring = String.format(format, qiyastring);
        tv_nowqiya.setText(qiyastring);

        img_nowcond.setImageResource(ConPictureUtils.getInstance().getImageResourceByCondCode(heWeatherDataService30.getNow().getCond().getCode()));

    }

    public void setSharePreferenceData() {
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());

        String api = sharedPreferences.getString("api", null);
        int api_int = Integer.parseInt(api);
        if(isAdded()) {
            GradientDrawable mygrad = (GradientDrawable)tv_api.getBackground();
            if (api_int <= 50) {
                mygrad.setColor(getResources().getColor(R.color.green));//优
                tv_api.setText("优");
            } else if (api_int <= 100) {
                mygrad.setColor(getResources().getColor(R.color.liang));//良
                tv_api.setText("良");
            } else if (api_int <= 150) {
                mygrad.setColor(getResources().getColor(R.color.qingdu));//轻度
                tv_api.setText("轻度");
            } else if (api_int <= 200) {
                mygrad.setColor(getResources().getColor(R.color.zhongdu1));//中度
                tv_api.setText("中度");
            } else if (api_int <= 300) {
                mygrad.setColor(getResources().getColor(R.color.zhongdu2));//重度
                tv_api.setText("重度");
            } else if (api_int <= 500) {
                mygrad.setColor(getResources().getColor(R.color.yanzhong));//严重
                tv_api.setText("严重");
            }
        }
        tv_nowweek.setText(sharedPreferences.getString("nowweek", null));

        String format = getResources().getString(R.string.refreshtime);
        String updatetime= String.format(format, sharedPreferences.getString("updatetime", null));
        tv_updatetime.setText(updatetime);

        format = getResources().getString(R.string.temperature);
        String temperature = String.format(format,sharedPreferences.getString("nowtmp", null));
        tv_nowtmp.setText(temperature);

        tv_nowcond.setText(sharedPreferences.getString("nowcond", null));

        String windstring = sharedPreferences.getString("nowwind", null);
        if(!windstring.equals("微风")){
            format = getResources().getString(R.string.wind_power);
            windstring= String.format(format ,windstring);
        }
        tv_nowwind.setText(windstring);

        String humiditystring = sharedPreferences.getString("nowhumidity", null);
        format = getResources().getString(R.string.humidity);
        humiditystring = String.format(format, humiditystring);
        tv_nowhumidity.setText(humiditystring);

        tv_nowziwaixian.setText(sharedPreferences.getString("nowziwaixian", null));

        String qiyastring = sharedPreferences.getString("nowqiya", null);
        format = getResources().getString(R.string.qiya);
        qiyastring = String.format(format, qiyastring);
        tv_nowqiya.setText(qiyastring);

        img_nowcond.setImageResource(sharedPreferences.getInt("nowimg", 0));
    }

}
