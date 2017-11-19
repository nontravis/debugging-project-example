package com.example.thekhaeng.debuggingseriesexample.database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

@Entity(tableName = "data_table")
public class DataEntity{

    @PrimaryKey
    public final int id;
    public String name;
    public String data;


    public DataEntity( int id, String name, String data) {
        this.id = id;
        this.name = name;
        this.data  = data;
    }

}
