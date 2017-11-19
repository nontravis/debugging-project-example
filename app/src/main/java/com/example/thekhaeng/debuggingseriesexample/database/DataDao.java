package com.example.thekhaeng.debuggingseriesexample.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by「 The Khaeng 」on 18 Nov 2017 :)
 */

@Dao
public interface DataDao{

    @Insert( onConflict = OnConflictStrategy.REPLACE )
    void addData( DataEntity data );

    @Query( "select * from data_table" )
    Single<List<DataEntity>> getAllDataList();

    @Query( "select * from data_table where id = :id" )
    Single<List<DataEntity>> getData( long id );

    @Update( onConflict = OnConflictStrategy.REPLACE )
    void updateData( DataEntity data );

    @Query( "delete from data_table" )
    void removeAllDataList();
}
