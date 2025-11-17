package com.example.testproject.database.entities;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public abstract UserDAO userDAO();

    public static synchronized AppDatabase getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "pokedex_database").fallbackToDestructiveMigration().addCallback(prepopulateCallback).build();
        }
        return instance;
    }

    private static RoomDatabase.Callback prepopulateCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new Thread(() -> {
                UserDAO userDAO = instance.userDAO();

                if(userDAO.getUserCount() == 0) {
                    User user1 = new User("testuser1", "testuser1", false);
                    User user2 = new User("admin2", "admin2", true);
                    userDAO.insertUser(user1);
                    userDAO.insertUser(user2);
                }
            }).start();
        }
    };

}
