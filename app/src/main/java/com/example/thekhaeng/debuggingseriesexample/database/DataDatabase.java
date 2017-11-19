package com.example.thekhaeng.debuggingseriesexample.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

@Database( entities = {DataEntity.class},
           version = 1,
           exportSchema = false )
public abstract class DataDatabase extends RoomDatabase{

    private static DataDatabase instance;

    public abstract DataDao getDataDao();

    public static DataDatabase getDatabase( Context context ){
        if( instance == null ){
            instance =
                    Room.databaseBuilder( context, DataDatabase.class, "example_database" )
                            .allowMainThreadQueries()
                            .build();
        }
        return instance;
    }
}
