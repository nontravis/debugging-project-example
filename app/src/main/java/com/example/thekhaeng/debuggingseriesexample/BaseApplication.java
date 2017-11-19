package com.example.thekhaeng.debuggingseriesexample;

import android.app.Application;

import com.example.thekhaeng.debuggingseriesexample.database.DataDatabase;
import com.example.thekhaeng.debuggingseriesexample.database.DataEntity;
import com.example.thekhaeng.debuggingseriesexample.setting.InitSetting;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

public abstract class BaseApplication extends Application{

    @Override
    public void onCreate(){
        super.onCreate();
        initFirstSetting();
        initFirstDatabase();
    }

    public void callFromConsole(){
        Timber.i( "callFromConsole: " );
    }

    private void initFirstSetting(){
        InitSetting.init( this )
                .ifFirstRunApplication() // important
                .persistString( R.string.setting_1, "setting_value_1" )
                .persistString( R.string.setting_2, "setting_value_2" )
                .persistString( R.string.setting_3, "setting_value_3" );
    }

    private void initFirstDatabase(){
        DataDatabase database = DataDatabase.getDatabase( getApplicationContext() );
        database.getDataDao()
                .getAllDataList()
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() )
                .subscribe( new SingleObserver<List<DataEntity>>(){
                    @Override
                    public void onSubscribe( Disposable d ){

                    }

                    @Override
                    public void onSuccess( List<DataEntity> dataList ){
                        Timber.i( "onSuccess: init database" );
                        if( dataList.isEmpty() ){
                            database.getDataDao().addData( new DataEntity( 1, "name_1", "data_1" ) );
                            database.getDataDao().addData( new DataEntity( 2, "name_2", "data_2" ) );
                            database.getDataDao().addData( new DataEntity( 3, "name_3", "data_3" ) );
                            database.getDataDao().addData( new DataEntity( 4, "name_4", "data_4" ) );
                        }

                    }

                    @Override
                    public void onError( Throwable e ){
                        Timber.i( "onError: cannot init database" );
                    }
                } );

    }
}
