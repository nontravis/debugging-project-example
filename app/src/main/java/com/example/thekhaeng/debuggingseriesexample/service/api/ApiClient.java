package com.example.thekhaeng.debuggingseriesexample.service.api;

import okhttp3.OkHttpClient;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

public class ApiClient{

    private static ApiClient instance;
    private final OkHttpClient okHttpClient;

    public static OkHttpClient getInstance(){
        if( instance == null ){
            instance = new ApiClient();
        }
        return instance.okHttpClient;
    }

    private ApiClient(){
        okHttpClient = new OkHttpClient.Builder()
                .build();
    }

}
