package com.example.ryanlee.rainbowweather.bean;

import java.io.Serializable;

/**
 * Created by Ryanlee on 2016/8/4 0004.
 */
public class City implements Serializable {
    private int id;
    private String city_id;
    private String city_name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String toString(){
        return this.city_id + this.city_name;
    }

}
