package com.example.ryanlee.rainbowweather.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ryanlee on 2016/8/6 0006.
 */
public class CityResult {
    @SerializedName("city_info")
    @Expose
    private List<CityInfo> cityInfo = new ArrayList<CityInfo>();
    @SerializedName("status")
    @Expose
    private String status;

    /**
     *
     * @return
     * The cityInfo
     */
    public List<CityInfo> getCityInfo() {
        return cityInfo;
    }

    /**
     *
     * @param cityInfo
     * The city_info
     */
    public void setCityInfo(List<CityInfo> cityInfo) {
        this.cityInfo = cityInfo;
    }

    /**
     *
     * @return
     * The status
     */
    public String getStatus() {
        return status;
    }

    /**
     *
     * @param status
     * The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
