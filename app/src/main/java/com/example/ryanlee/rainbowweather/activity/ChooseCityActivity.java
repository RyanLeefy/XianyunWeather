package com.example.ryanlee.rainbowweather.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ryanlee.rainbowweather.R;
import com.example.ryanlee.rainbowweather.adapter.CityAdapter;
import com.example.ryanlee.rainbowweather.bean.City;
import com.example.ryanlee.rainbowweather.presenter.CityPresenter;
import com.example.ryanlee.rainbowweather.presenter.ICityPresenter;
import com.example.ryanlee.rainbowweather.ui.CornerListView;
import com.example.ryanlee.rainbowweather.ui.SearchView;
import com.example.ryanlee.rainbowweather.view.ICityView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryanlee on 2016/8/12 0012.
 */
public class ChooseCityActivity extends Activity implements ICityView{

    private CornerListView ls_city;
    private ArrayList<City> cities;
    CityAdapter adapter;
    private ICityPresenter presenter;
    private SearchView searchView;

    private final static int NORMAL = 10001;
    private final static int SEARCH = 10002;
    private int state = NORMAL;      //是否搜索状态


    LinearLayout loadingLayout;
    private LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT);

    private LinearLayout.LayoutParams ffLayoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT);

    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choosecity);

        ls_city = (CornerListView)findViewById(R.id.ls_city);
        searchView = (SearchView)findViewById(R.id.searchview);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.HORIZONTAL);
        progressBar = new ProgressBar(this);
        layout.addView(progressBar, mLayoutParams);
        TextView textView = new TextView(this);
        textView.setText("正在加载...");
        textView.setGravity(Gravity.CENTER_VERTICAL);
        layout.addView(textView, ffLayoutParams);
        layout.setGravity(Gravity.CENTER);
        loadingLayout = new LinearLayout(this);
        loadingLayout.addView(layout, mLayoutParams);
        loadingLayout.setGravity(Gravity.CENTER);

        //添加一个FrameLayout包裹在footerlayout外面，这样才可以控制footerlayout的显示
        FrameLayout footerLayoutHolder = new FrameLayout(this);
        footerLayoutHolder.addView(loadingLayout, 0, new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,
                FrameLayout.LayoutParams.WRAP_CONTENT));

        ls_city.addFooterView(footerLayoutHolder, null, false);

        //footerlayout初始看不见
        loadingLayout.setVisibility(View.GONE);

        searchView.setSearchViewListener(new SearchView.SearchViewListener(){
            @Override
            public void onRefreshAutoComplete(String text) {
                //Log.d("Search", "onRefreshAutoComplete");
            }

            @Override
            public void onSearch(String text) {    //点击搜索按钮
                //Log.d("Search", "onSearch");
                if(text != null) {
                    searchView.clearFocus();
                    //Toast.makeText(ChooseCityActivity.this, "搜索中……", Toast.LENGTH_SHORT).show();
                    presenter.performOnSearch(text);
                    state = SEARCH;
                }
            }
        } );


        ls_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SharedPreferences perPreferences = getSharedPreferences("isCitySelect", MODE_PRIVATE);
                Boolean isCitySelect = perPreferences.getBoolean("isCitySelect", false);

                if(isCitySelect){          //选过城市，即从MainActivity中来
                    presenter.performOnClickforresult((City) parent.getAdapter().getItem(position));
                }else {                    //没选过城市，即从WelcomeActivity中来
                    SharedPreferences.Editor editor = perPreferences.edit();
                    editor.putBoolean("isCitySelect", true);
                    editor.commit();
                    presenter.performOnClick((City) parent.getAdapter().getItem(position));
                }

            }
        });


        ls_city.setOnScrollListener(new AbsListView.OnScrollListener(){
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if(state == NORMAL) {
                    switch (scrollState) {
                        case AbsListView.OnScrollListener.SCROLL_STATE_IDLE: // 当不迁移转变时
                            // 断定迁移转变到底部
                            if ((view.getLastVisiblePosition()) == (view.getCount()) - 1) {
                                loadingLayout.setVisibility(View.VISIBLE);   //滚动到底部，显示loadingLayout，并开始读取数据显示
                                presenter.ShowCity(view.getCount() / 50);
                                //在这里添加操纵
                            }
                            break;
                    }
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });

        presenter=new CityPresenter(this);
        presenter.onCreate();
    }

    @Override
    public void updateView(List<City> cities) {
        adapter.updateAdapter(cities);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setAdapter(CityAdapter adapter) {
        this.adapter = adapter;
        ls_city.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void hideLoadingLayout() {
        loadingLayout.setVisibility(View.GONE);
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        presenter.onDestroy();
    }

}
