package com.example.yi;

import android.app.Application;
import android.util.Log;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("jett","MyApplication onCreate()");
    }
}
