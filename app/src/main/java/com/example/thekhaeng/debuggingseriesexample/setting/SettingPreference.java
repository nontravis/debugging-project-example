package com.example.thekhaeng.debuggingseriesexample.setting;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

/**
 * Created by「 The Khaeng 」on 02 Oct 2017 :)
 */

public class SettingPreference{

    private final Context context;

    public SettingPreference( Context context ){
        this.context = context;
    }

    public boolean shouldPersist(){
        return getSharedPreferences() != null;
    }


    public boolean persistString( String key, String value ){
        if( shouldPersist() ){
            // Shouldn't store null
            if( TextUtils.equals( value, getPersistedString( key, null ) ) ){
                // It's already there, so the same as persisting
                return true;
            }

            SharedPreferences.Editor editor = getSharedPreferences().edit();
            editor.putString( key, value );
            editor.apply();
            return true;
        }
        return false;
    }

    public String getPersistedString( String key,
                                      String defaultValue ){
        if( !shouldPersist() ){
            return defaultValue;
        }
        return getSharedPreferences().getString( key, defaultValue );
    }

    public boolean persistedBoolean( String key,
                                     boolean value,
                                     boolean shouldPersist ){
        if( shouldPersist ){

            if( value == getPersistedBoolean( key, !value ) ){
                // It's already there, so the same as persisting
                return true;
            }
            SharedPreferences.Editor editor = getSharedPreferences().edit();
            editor.putBoolean( key, value );
            editor.apply();
            return true;
        }
        return false;
    }


    public boolean getPersistedBoolean( String key,
                                        boolean defaultValue ){
        if( !shouldPersist() ){
            return defaultValue;
        }
        return getSharedPreferences().getBoolean( key, defaultValue );
    }


    public SharedPreferences getSharedPreferences(){
        return PreferenceManager.getDefaultSharedPreferences( context );
    }

}
