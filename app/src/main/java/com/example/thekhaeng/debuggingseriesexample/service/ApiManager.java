package com.example.thekhaeng.debuggingseriesexample.service;

import com.example.thekhaeng.debuggingseriesexample.service.api.Api;
import com.example.thekhaeng.debuggingseriesexample.service.api.ApiService;
import com.example.thekhaeng.debuggingseriesexample.service.response.DataResponse;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

public class ApiManager{

    private static ApiManager instance;

    public static ApiManager getInstance(){
        if( instance == null ){
            instance = new ApiManager();
        }

        return instance;
    }

    public Single<DataResponse> requestExampleData(){
        return ApiService.newInstance()
                .createApi( Api.class )
                .requestExampleData()
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() );
    }

    public Single<List<DataResponse>> requestZipExampleData(){
        return Single.zip(
                ApiService.newInstance()
                        .createApi( Api.class )
                        .requestZip1ExampleData(),
                ApiService.newInstance()
                        .createApi( Api.class )
                        .requestZip2ExampleData(),
                ( dataResponse, dataResponse2 ) -> {
                    List<DataResponse> dataList = new ArrayList<>();
                    dataList.add( dataResponse );
                    dataList.add( dataResponse2 );
                    return dataList;
                } )
                .subscribeOn( Schedulers.io() )
                .observeOn( AndroidSchedulers.mainThread() );
    }

}
