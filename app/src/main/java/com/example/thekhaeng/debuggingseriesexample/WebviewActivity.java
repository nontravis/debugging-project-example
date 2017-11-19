package com.example.thekhaeng.debuggingseriesexample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.SuppressLint;
import android.content.pm.ApplicationInfo;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class WebviewActivity extends AppCompatActivity{

    private WebView webview;
    private ProgressBar progress;

    @Override
    protected void onCreate( Bundle savedInstanceState ){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_webview );
        webview = findViewById( R.id.webview );
        progress = findViewById( R.id.progressBar );

        setupWebview( webview );

        webview.loadUrl( "https://www.facebook.com/thekhaeng.io" );
    }

    @SuppressLint( "SetJavaScriptEnabled" )
    private void setupWebview( WebView webview ){
        webview.setWebViewClient( getClient() );
        webview.getSettings().setJavaScriptEnabled( true );
        webview.getSettings().setSupportZoom( true );
        webview.getSettings().setBuiltInZoomControls( true );
        webview.setInitialScale( 1 );

        if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT ){
            // WebView debugging is not affected by the state of the debuggable flag
            // in the application's manifest. If you want to enable WebView debugging only
            // when debuggable is true, test the flag at runtime
            if( 0 != ( getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE ) ){
                // for debug webview in chrome developer tools
                WebView.setWebContentsDebuggingEnabled( true );
            }
        }
    }

    @NonNull
    private WebViewClient getClient(){
        return new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading( WebView view, WebResourceRequest request ){
                return false;
            }

            @Override
            public void onPageStarted( WebView view, String url, Bitmap favicon ){
                super.onPageStarted( view, url, favicon );
                progress.setVisibility( View.VISIBLE );
            }

            @Override
            public void onPageFinished( WebView view, String url ){
                super.onPageFinished( view, url );
                progress
                        .animate()
                        .alpha( 0f )
                        .setDuration( 200 )
                        .setListener( new AnimatorListenerAdapter(){
                            @Override
                            public void onAnimationEnd( Animator animation ){
                                super.onAnimationEnd( animation );
                                progress.setVisibility( View.GONE );
                            }
                        } );
            }
        };
    }
}
