package com.example.thekhaeng.debuggingseriesexample;

import android.app.Application;

import timber.log.Timber;

/**
 * Created by「 The Khaeng 」on 17 Nov 2017 :)
 */

public class MainApplication extends BaseApplication{

    @Override
    public void onCreate(){
        super.onCreate();
        Timber.plant(new ReleaseTree());
    }
}
