package com.example.task.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.task.models.UserDetails;

@Database(entities = UserDetails.class,exportSchema = false,version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract DAO getDAO();
    static MyDatabase INSTANCE=null;

    public static MyDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (MyDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    MyDatabase.class, "MyDB")
                            .allowMainThreadQueries()
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
