package com.example.thekhaeng.debuggingseriesexample.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.StringRes;

/**
 * Created by thekhaeng on 6/14/2017 AD.
 */

public class InitSetting{
    public static final String FIRST_RUN_APPLICATION = "first_run_application";
    private final Context context;
    private SettingPreference settingPreference;
    private boolean isFirstRun = true;

    public static InitSetting init( Context context ){
        return new InitSetting( context );
    }

    private InitSetting( Context context ){
        this.context = context;
        settingPreference = new SettingPreference( context );
    }

    public InitSetting persistString( String key, String value ){
        if( isFirstRun ) settingPreference.persistString( key, value );
        return this;
    }

    public InitSetting persistString( @StringRes int stringId, String value ){
        return persistString( getString( stringId ), value );
    }


    private String getString( @StringRes int stringId ){
        return context.getResources().getString( stringId );
    }

    public InitSetting ifFirstRunApplication(){
        isFirstRun = settingPreference.getPersistedBoolean( FIRST_RUN_APPLICATION, true );
        if( isFirstRun ){
            SharedPreferences.Editor editor = settingPreference.getSharedPreferences().edit();
            editor.putBoolean( FIRST_RUN_APPLICATION, false );
            editor.apply();
        }
        return this;
    }
}
