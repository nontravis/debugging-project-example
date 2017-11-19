package com.example.thekhaeng.debuggingseriesexample;

import android.annotation.SuppressLint;
import android.util.Log;

import com.facebook.stetho.inspector.console.CLog;
import com.facebook.stetho.inspector.console.ConsolePeerManager;
import com.facebook.stetho.inspector.protocol.module.Console;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import timber.log.Timber;

/**
 * Created by TheKhaeng on 9/8/2016.
 */

public class DebugTree extends Timber.DebugTree{

    private static final int CALL_STACK_INDEX = 5;
    private static final Pattern ANONYMOUS_CLASS = Pattern.compile("(\\$\\d+)+$");

    @SuppressLint( "DefaultLocale" )
    @Override
    protected void log( int priority, String tag, String message, Throwable t ){
        StackTraceElement[] stackTrace = new Throwable().getStackTrace();

        if (stackTrace.length <= CALL_STACK_INDEX) {
            throw new IllegalStateException(
                    "Synthetic stacktrace didn't have enough elements: are you using proguard?");
        }

        String clazz = extractClassName( stackTrace[CALL_STACK_INDEX] );
        int lineNumber = stackTrace[CALL_STACK_INDEX].getLineNumber();
        String newMessage = String.format("(%s:%d) - %s", clazz, lineNumber, message);

        super.log( priority, tag, newMessage, t );

        stethoLog( priority, message );
    }

    private void stethoLog( int priority, String message ){
        ConsolePeerManager peerManager = ConsolePeerManager.getInstanceOrNull();
        if( peerManager == null ){
            return;
        }

        Console.MessageLevel logLevel;

        switch( priority ){
            case Log.VERBOSE:
            case Log.DEBUG:
                logLevel = Console.MessageLevel.DEBUG;
                break;
            case Log.INFO:
                logLevel = Console.MessageLevel.LOG;
                break;
            case Log.WARN:
                logLevel = Console.MessageLevel.WARNING;
                break;
            case Log.ERROR:
            case Log.ASSERT:
                logLevel = Console.MessageLevel.ERROR;
                break;
            default:
                logLevel = Console.MessageLevel.LOG;
        }

        CLog.writeToConsole(
                logLevel,
                Console.MessageSource.OTHER,
                message
        );
    }

    @Override
    protected String createStackElementTag( StackTraceElement element ){
        return super.createStackElementTag( element ) + ":" + element.getLineNumber();
    }

    /**
     * Extract the class name without any anonymous class suffixes (e.g., `Foo$1`
     * becomes `Foo`).
     */
    private String extractClassName(StackTraceElement element) {
        String tag = element.getFileName();
        Matcher m = ANONYMOUS_CLASS.matcher( tag );
        if (m.find()) {
            tag = m.replaceAll("");
        }
        return tag;
    }
}
