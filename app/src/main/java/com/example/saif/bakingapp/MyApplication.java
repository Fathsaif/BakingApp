package com.example.saif.bakingapp;

import android.app.Application;
import android.content.res.Configuration;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Saif on 01/06/2017.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
