package com.example.thekhaeng.debuggingseriesexample.service.api;

import com.example.thekhaeng.debuggingseriesexample.BuildConfig;
import com.example.thekhaeng.debuggingseriesexample.service.interceptor.HttpLogger;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

public class ApiService{
    private static final String TAG = ApiService.class.getSimpleName();
    private static final int TIMEOUT = 20000;

    public static ApiService newInstance(){
        return new ApiService();
    }

    public Api createApi( Class<Api> apiClass ){
        return new Retrofit.Builder()
                .baseUrl( URL.BASE )
                .addConverterFactory( GsonConverterFactory.create( new GsonBuilder().setPrettyPrinting().create() ) )
                .client( getOkHttpClient() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .build()
                .create( apiClass );
    }

    protected HttpLoggingInterceptor getDefaultHttpLoggingInterceptor(){
        if( BuildConfig.DEBUG ){
            return new HttpLoggingInterceptor( new HttpLogger() ).setLevel( HttpLoggingInterceptor.Level.BODY );
        }else{
            return new HttpLoggingInterceptor().setLevel( HttpLoggingInterceptor.Level.NONE );
        }
    }


    /* =========================== Private method =============================================== */
    private OkHttpClient getOkHttpClient(){
        return ApiClient.getInstance()
                .newBuilder()
                .addNetworkInterceptor( new StethoInterceptor() )
                .addNetworkInterceptor( getDefaultHttpLoggingInterceptor() )
                .readTimeout( TIMEOUT, TimeUnit.MILLISECONDS )
                .writeTimeout( TIMEOUT, TimeUnit.MILLISECONDS )
                .connectTimeout( TIMEOUT, TimeUnit.MILLISECONDS )
                .build();
    }
}
