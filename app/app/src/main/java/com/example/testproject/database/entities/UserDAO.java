package com.example.testproject.database.entities;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDAO {
    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM users WHERE username = :username AND password = :password LIMIT 1")
    User validateCredentials(String username, String password);

    @Query("SELECT COUNT(*) FROM users WHERE username = :username")
    int checkUsernameExists(String username);

    @Query("SELECT * FROM users WHERE userID = :userID LIMIT 1")
    User getUserByID(int userID);

    @Query("SELECT COUNT(*) FROM users")
    int getUserCount();
}
