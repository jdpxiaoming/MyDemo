package com.example.poe.myapplication.utils;

import android.content.Context;

import com.example.poe.myapplication.MyApplication;

/**
 * Created by poe on 16-7-20.
 */
public class UiUtils {
    /**
     * dp转化为 px
     *
     * @param dpValue
     * @return
     */
    public static int dip2px(float dpValue) {
        final float scale = MyApplication.instance().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(float pxValue) {
        final float scale = MyApplication.instance().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
