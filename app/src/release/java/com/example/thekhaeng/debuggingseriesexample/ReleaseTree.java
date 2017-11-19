package com.example.thekhaeng.debuggingseriesexample;

import android.util.Log;

import timber.log.Timber;

/**
 * Created by TheKhaeng on 9/1/2016.
 */

public class ReleaseTree extends Timber.Tree{

    public static final int MAX_LOG_LENGTH = 4000;

    @Override
    protected boolean isLoggable(String tag, int priority ){
        if( priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO ){
            return false;
        }
        // Only log WARN, ERROR, WTF
        return true;
    }

    @Override
    protected void log( int priority, String tag, String message, Throwable t ){
        if( isLoggable(tag, priority ) ){
            //Report Crash
            if( priority == Log.ERROR || t != null ){
                // Crashlytic.log(e);
            }

            // Message is short enough, does not need
            if( message.length() < MAX_LOG_LENGTH ){
                switch( priority ){
                    case Log.ASSERT:
                        Log.wtf( tag, message );
                        break;
                    case Log.ERROR:
                        Log.e( tag, message );
                        break;
                    default:
                        Log.println( priority, tag, message );
                        break;
                }
                return;
            }

            // Split line by '\n'
            for( int i = 0, length = message.length(); i < length; i++ ){
                int newline = message.indexOf( '\n', i );
                newline = newline != -1 ? newline : length;
                do{
                    int end = Math.min( length, i + MAX_LOG_LENGTH );
                    String part = message.substring( i, end );
                    switch( priority ){
                        case Log.ASSERT:
                            Log.wtf( tag, part );
                            break;
                        case Log.ERROR:
                            Log.e( tag, part );
                            break;
                        default:
                            Log.println( priority, tag, part );
                            break;
                    }
                    i = end;
                }while( i < newline );
            }
        }
    }


}
