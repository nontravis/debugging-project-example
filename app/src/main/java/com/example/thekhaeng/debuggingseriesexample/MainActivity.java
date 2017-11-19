package com.example.thekhaeng.debuggingseriesexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.thekhaeng.debuggingseriesexample.base.LogActivity;
import com.example.thekhaeng.debuggingseriesexample.service.ApiManager;
import com.example.thekhaeng.debuggingseriesexample.service.response.DataResponse;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

public class MainActivity extends LogActivity{

    private TextView tvCommand;

    @Override
    protected void onCreate( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        findViewById( R.id.btn_request_example )
                .setOnClickListener( onClickListener() );
        findViewById( R.id.btn_request_zip_example )
                .setOnClickListener( onClickListener() );
        findViewById( R.id.btn_webview_example )
                .setOnClickListener( onClickListener() );
        tvCommand = findViewById( R.id.tv_command );
    }


    @NonNull
    private View.OnClickListener onClickListener(){
        return v -> {
            int i = v.getId();
            if( i == R.id.btn_request_example ){
                ApiManager.getInstance().requestExampleData()
                        .subscribe( new SingleObserver<DataResponse>(){
                            @Override
                            public void onSubscribe( Disposable d ){

                            }

                            @Override
                            public void onSuccess( DataResponse dataResponse ){
                                tvCommand.append(  "\nonSuccess: "+ dataResponse );
                            }

                            @Override
                            public void onError( Throwable e ){
                                tvCommand.append(  "\nonError: "+ e.getMessage() );
                            }
                        } );
            }else if( i == R.id.btn_request_zip_example ){
                ApiManager.getInstance().requestZipExampleData()
                        .subscribe( new SingleObserver<List<DataResponse>>(){
                            @Override
                            public void onSubscribe( Disposable d ){

                            }

                            @Override
                            public void onSuccess( List<DataResponse> dataResponses ){
                                tvCommand.append(  "\nonSuccess: "+ dataResponses );
                            }

                            @Override
                            public void onError( Throwable e ){
                                tvCommand.append(  "\nonError: "+ e.getMessage() );
                            }
                        } );
            }else if( i == R.id.btn_webview_example ){
                Intent intent = new Intent( MainActivity.this, WebviewActivity.class );
                startActivity( intent );
            }
        };
    }
}
