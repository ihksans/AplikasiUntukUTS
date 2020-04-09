

package com.example.aplikasiuntukuts.data;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;
import androidx.annotation.VisibleForTesting;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Cheese.class}, version = 1,exportSchema = false)
public abstract class SampleDatabase extends RoomDatabase {
    public abstract CheeseDao cheese();
    private static SampleDatabase sInstance;

    private static volatile SampleDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SampleDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SampleDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SampleDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);

            // If you want to keep data through app restarts,
            // comment out the following block
            databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more words, just add them.
                CheeseDao dao = INSTANCE.cheese();
                dao.deleteAll();

//                Cheese word = new Cheese("Hello");
//                dao.insert(word);
//                word = new Cheese("World");
//                dao.insert(word);
                for (int i = 0; i < Cheese.CHEESES.length; i++) {
                    Cheese data = new Cheese(Cheese.CHEESES[i]);
                    dao.insert(data);
                }
            });
        }
    };

    public static synchronized SampleDatabase getInstance(Context context) {
        if (sInstance == null) {
            sInstance = Room
                    .databaseBuilder(context.getApplicationContext(), SampleDatabase.class, "ex")
                    .addCallback(sRoomDatabaseCallback)
                    .build();
//            sInstance.populateInitialData(); // to Inserts the dummy data into the database if it is currently empty
        }
        return sInstance;
    }

}
