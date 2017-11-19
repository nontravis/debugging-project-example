package com.example.thekhaeng.debuggingseriesexample;

import com.facebook.stetho.Stetho;
import com.facebook.stetho.rhino.JsRuntimeReplFactoryBuilder;

import timber.log.Timber;

/**
 * Created by「 The Khaeng 」on 17 Nov 2017 :)
 */

public class MainApplication extends BaseApplication{

    @Override
    public void onCreate(){
        super.onCreate();
        Timber.plant( new DebugTree() );

        Stetho.initialize(
//                Stetho.defaultInspectorModulesProvider( this )
                Stetho.newInitializerBuilder( this )
//                        .enableWebKitInspector( Stetho.defaultInspectorModulesProvider( this ) )
                        .enableWebKitInspector(
                                () -> new Stetho.DefaultInspectorModulesBuilder( MainApplication.this )
                                        .runtimeRepl(
                                                new JsRuntimeReplFactoryBuilder( MainApplication.this )
                                                        .addVariable( "foo", "Assign to foo variable" )
                                                        .build()
                                        ).finish() )
                        .enableDumpapp(
                                () -> new Stetho.DefaultDumperPluginsBuilder( MainApplication.this )
                                        .provide( new HelloWorldDumperPlugin() )
                                        .finish() )
                        .build()
        );
    }
}
