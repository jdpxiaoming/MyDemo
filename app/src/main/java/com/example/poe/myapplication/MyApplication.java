package com.example.poe.myapplication;

import android.app.Application;

/**
 * Created by poe on 16-7-20.
 */
public class MyApplication extends Application{

    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this ;
    }

    public static MyApplication instance() {
        return mInstance;
    }
}
