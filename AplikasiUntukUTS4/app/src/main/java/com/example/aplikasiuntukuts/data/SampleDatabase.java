

package com.example.aplikasiuntukuts.data;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.VisibleForTesting;

@Database(entities = {Cheese.class}, version = 1)
public abstract class SampleDatabase extends RoomDatabase {


    public abstract CheeseDao cheese();


    private static SampleDatabase sInstance;


    public static synchronized SampleDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), SampleDatabase.class, "ex")
                    .build();
            sInstance.populateInitialData(); // to Inserts the dummy data into the database if it is currently empty
        }
        return sInstance;
    }


    @VisibleForTesting
    public static void switchToInMemory(Context context) {
        sInstance = Room.inMemoryDatabaseBuilder(context.getApplicationContext(),
                SampleDatabase.class).build();
    }

    private void populateInitialData() {
        if (cheese().count() == 0) {
            runInTransaction(new Runnable() {
                @Override
                public void run() {
                    Cheese cheese = new Cheese();
                    for (int i = 0; i < Cheese.CHEESES.length; i++) {
                        cheese.name = Cheese.CHEESES[i];
                        cheese().insert(cheese);
                    }
                }
            });
        }
    }

}
