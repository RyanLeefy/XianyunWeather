package com.example.ryanlee.rainbowweather.bean;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Ryanlee on 2016/8/7 0007.
 */
public class CityInfo {

    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("cnty")
    @Expose
    private String cnty;
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("lat")
    @Expose
    private String lat;
    @SerializedName("lon")
    @Expose
    private String lon;
    @SerializedName("prov")
    @Expose
    private String prov;

    /**
     *
     * @return
     * The city
     */
    public String getCity() {
        return city;
    }

    /**
     *
     * @param city
     * The city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     *
     * @return
     * The cnty
     */
    public String getCnty() {
        return cnty;
    }

    /**
     *
     * @param cnty
     * The cnty
     */
    public void setCnty(String cnty) {
        this.cnty = cnty;
    }

    /**
     *
     * @return
     * The id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id
     * The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return
     * The lat
     */
    public String getLat() {
        return lat;
    }

    /**
     *
     * @param lat
     * The lat
     */
    public void setLat(String lat) {
        this.lat = lat;
    }

    /**
     *
     * @return
     * The lon
     */
    public String getLon() {
        return lon;
    }

    /**
     *
     * @param lon
     * The lon
     */
    public void setLon(String lon) {
        this.lon = lon;
    }

    /**
     *
     * @return
     * The prov
     */
    public String getProv() {
        return prov;
    }

    /**
     *
     * @param prov
     * The prov
     */
    public void setProv(String prov) {
        this.prov = prov;
    }

}