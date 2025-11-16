package com.example.project2_group4.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class}, version = 1)
public class PokeDatabase extends RoomDatabase {
    private static PokeDatabase instance;

    public abstract UserDAO userDAO();

    public static synchronized PokeDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), PokeDatabase.class, "pokedex_database").fallbackToDestructiveMigration().addCallback(prepopulationCallBack).build();
        }
        return instance;
    }

}
