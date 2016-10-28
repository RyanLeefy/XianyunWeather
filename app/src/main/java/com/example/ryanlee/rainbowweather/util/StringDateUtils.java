package com.example.ryanlee.rainbowweather.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Ryanlee on 2016/8/9 0009.
 */
public class StringDateUtils {

    private static StringDateUtils stringDateUtils = new StringDateUtils();

    public StringDateUtils(){
    }

    public static StringDateUtils getInstance(){
        return stringDateUtils;
    }


    public String toFormatStringDate(String date){
        Calendar c = createCalendar(date);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DATE);
        String datestring = month + "/" + day;
        return datestring;
    }

    public String toFormatStringWeek(String date) {
        if(date.equals("今天"))
            return date;
        Calendar c = createCalendar(date);
        int week = c.get(Calendar.DAY_OF_WEEK);
        String weekstring = null;
        switch (week){
            case 1:
                weekstring = "周日";break;
            case 2:
                weekstring = "周一";break;
            case 3:
                weekstring = "周二";break;
            case 4:
                weekstring = "周三";break;
            case 5:
                weekstring = "周四";break;
            case 6:
                weekstring = "周五";break;
            case 7:
                weekstring = "周六";break;
        }
        return weekstring;
    }

    private Calendar createCalendar(String date){
        Calendar c= Calendar.getInstance();
        Date d = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d = format.parse(date);
        } catch (Exception e){
            e.printStackTrace();
        }
        c.setTime(d);
        return c;
    }

}
