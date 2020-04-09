
package com.example.aplikasiuntukuts.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import android.database.Cursor;
import android.content.ContentValues;
import android.provider.BaseColumns;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;


@Dao
public interface CheeseDao {


    @Query("SELECT COUNT(*) FROM " + Cheese.TABLE_NAME)
    int count();


    @Insert
    long insert(Cheese cheese);


    @Insert
    long[] insertAll(Cheese[] cheeses);


    @Query("SELECT * FROM " + Cheese.TABLE_NAME)
    Cursor selectAll();

    @Query("SELECT * FROM " + Cheese.TABLE_NAME + " WHERE " + Cheese.COLUMN_ID + " = :id")
    Cursor selectById(long id);


    @Query("DELETE FROM " + Cheese.TABLE_NAME + " WHERE " + Cheese.COLUMN_ID + " = :id")
    int deleteById(long id);


    @Update
    int update(Cheese cheese);

    @Query("DELETE FROM cheeses")
    void deleteAll();

    @Query("SELECT * from cheeses ORDER BY name ASC")
    LiveData<List<Cheese>> getAlphabetizedWords();


}
