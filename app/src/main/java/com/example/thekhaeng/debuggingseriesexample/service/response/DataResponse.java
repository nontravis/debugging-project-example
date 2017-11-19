package com.example.thekhaeng.debuggingseriesexample.service.response;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

public class DataResponse{

    private String data;

    public String getData(){
        return data;
    }

    public void setData( String data ){
        this.data = data;
    }

    @Override
    public String toString(){
        return "DataResponse{" +
                "data='" + data + '\'' +
                '}';
    }
}
