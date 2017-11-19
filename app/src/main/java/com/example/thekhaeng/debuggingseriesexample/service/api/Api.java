package com.example.thekhaeng.debuggingseriesexample.service.api;

import com.example.thekhaeng.debuggingseriesexample.service.response.DataResponse;

import io.reactivex.Single;
import retrofit2.http.GET;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

public interface Api{

    @GET(URL.REQUEST_EXAMPLE)
    Single<DataResponse> requestExampleData();

    @GET(URL.REQUEST_ZIP_1)
    Single<DataResponse> requestZip1ExampleData();

    @GET(URL.REQUEST_ZIP_2)
    Single<DataResponse> requestZip2ExampleData();
}
