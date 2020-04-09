
package com.example.aplikasiuntukuts.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import android.database.Cursor;



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

}
