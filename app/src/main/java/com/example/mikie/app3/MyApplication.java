package com.example.mikie.app3;

import android.app.Application;
import android.content.Context;

/**
 * Created by mikie on 07/10/2015.
 */
public class MyApplication extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static Context getContext() {
        return mContext;
    }
}