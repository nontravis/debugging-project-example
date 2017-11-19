package com.example.thekhaeng.debuggingseriesexample.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import timber.log.Timber;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

public abstract class LogActivity extends AppCompatActivity{

    @Override
    protected void onCreate( @Nullable Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );
        Timber.d( "onCreate: " + savedInstanceState );
    }

    @Override
    protected void onStart(){
        super.onStart();
        Timber.d( "onStart: " );
    }

    @Override
    protected void onResume(){
        super.onResume();
        Timber.d( "onResume: " );
    }

    @Override
    protected void onPause(){
        super.onPause();
        Timber.d( "onPause: " );
    }

    @Override
    protected void onStop(){
        super.onStop();
        Timber.d( "onStop: " );
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Timber.d( "onDestroy: " );
    }

    @Override
    protected void onSaveInstanceState( Bundle outState ){
        super.onSaveInstanceState( outState );
        Timber.d( "onSaveInstanceState: " + outState );
    }

    @Override
    protected void onRestoreInstanceState( Bundle savedInstanceState ){
        super.onRestoreInstanceState( savedInstanceState );
        Timber.d( "onRestoreInstanceState: " + savedInstanceState );
    }
}

