package com.example.ryanlee.rainbowweather.ui;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.TextView;

import com.example.ryanlee.rainbowweather.util.FontUtils;

/**
 * Created by Ryanlee on 2016/8/10 0010.
 */
public class CustomTextView extends TextView {

    private String mFontPath = "fonts/HUAWENXINGKAI.ttf";

    public CustomTextView(Context context) {
        super(context);
        init(context);
    }

    public CustomTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    public void setFontPath(String fontPath) {
        mFontPath = fontPath;

        if (!TextUtils.isEmpty(mFontPath)) {
            FontUtils.getInstance().replaceFontFromAsset(this, mFontPath);
        }
    }

    private void init(Context context) {
        if (!TextUtils.isEmpty(mFontPath)) {
            FontUtils.getInstance().replaceFontFromAsset(this, mFontPath);
        }
    }




}
