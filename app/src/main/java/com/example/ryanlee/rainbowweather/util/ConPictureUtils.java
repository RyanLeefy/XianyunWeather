package com.example.ryanlee.rainbowweather.util;

import com.example.ryanlee.rainbowweather.R;

/**
 * Created by Ryanlee on 2016/8/10 0010.
 */
public class ConPictureUtils {
    private static ConPictureUtils conPictureUtils = new ConPictureUtils();
    public ConPictureUtils(){

    }
    public static ConPictureUtils getInstance(){
        return conPictureUtils;
    }

    public int getImageResourceByCondCode(String Code){
        int r = 0;
        switch (Code){
            case "100": r = R.drawable.c100;break;
            case "101": r = R.drawable.c101;break;
            case "102": r = R.drawable.c102;break;
            case "103": r = R.drawable.c103;break;
            case "104": r = R.drawable.c104;break;
            case "200": r = R.drawable.c200;break;
            case "201": r = R.drawable.c201;break;
            case "202": r = R.drawable.c202;break;
            case "203": r = R.drawable.c203;break;
            case "204": r = R.drawable.c204;break;
            case "205": r = R.drawable.c205;break;
            case "206": r = R.drawable.c206;break;
            case "207": r = R.drawable.c207;break;
            case "208": r = R.drawable.c208;break;
            case "209": r = R.drawable.c209;break;
            case "210": r = R.drawable.c210;break;
            case "211": r = R.drawable.c211;break;
            case "212": r = R.drawable.c212;break;
            case "213": r = R.drawable.c213;break;
            case "300": r = R.drawable.c300;break;
            case "301": r = R.drawable.c301;break;
            case "302": r = R.drawable.c302;break;
            case "303": r = R.drawable.c303;break;
            case "304": r = R.drawable.c304;break;
            case "305": r = R.drawable.c305;break;
            case "306": r = R.drawable.c306;break;
            case "307": r = R.drawable.c307;break;
            case "308": r = R.drawable.c308;break;
            case "309": r = R.drawable.c309;break;
            case "310": r = R.drawable.c310;break;
            case "311": r = R.drawable.c311;break;
            case "312": r = R.drawable.c312;break;
            case "313": r = R.drawable.c313;break;
            case "400": r = R.drawable.c400;break;
            case "401": r = R.drawable.c401;break;
            case "402": r = R.drawable.c402;break;
            case "403": r = R.drawable.c403;break;
            case "404": r = R.drawable.c404;break;
            case "405": r = R.drawable.c405;break;
            case "407": r = R.drawable.c407;break;
            case "500": r = R.drawable.c500;break;
            case "501": r = R.drawable.c501;break;
            case "502": r = R.drawable.c502;break;
            case "503": r = R.drawable.c503;break;
            case "504": r = R.drawable.c504;break;
            case "506": r = R.drawable.c504;break;  //无图，暂用504
            case "507": r = R.drawable.c507;break;
            case "508": r = R.drawable.c508;break;
            case "900": r = R.drawable.c900;break;
            case "901": r = R.drawable.c901;break;
            case "999": r = R.drawable.c999;break;
        }
        return r;
    }

}
