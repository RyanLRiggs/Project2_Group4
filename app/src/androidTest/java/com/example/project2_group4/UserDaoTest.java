package com.example.project2_group4;

import static org.junit.Assert.*;

import android.content.Context;

import androidx.room.Room;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.project2_group4.database.entities.AppDatabase;
import com.example.project2_group4.database.entities.User;
import com.example.project2_group4.database.entities.UserDAO;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
//hi
@RunWith(AndroidJUnit4.class)
public class UserDaoTest {

    private AppDatabase db;
    private UserDAO userDao;

    @Before
    public void setUp() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();

        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class)
                .allowMainThreadQueries()
                .build();

        userDao = db.userDAO();
    }

    @After
    public void tearDown() {
        db.close();
    }

    // TEST 1: inserting a user increases the number of users in the DB
    @Test
    public void insertUser_increasesUserCount() {
        int before = userDao.getUserCount();

        User user = new User("testuser", "pass123", false);
        userDao.insertUser(user);

        int after = userDao.getUserCount();

        assertEquals("User count should increase by 1 after insert",
                before + 1, after);
    }

    // TEST 2: validateCredentials returns a user for correct login
    @Test
    public void validateCredentials_returnsUserForCorrectCredentials() {
        User user = new User("adminTest", "secret123", true);
        userDao.insertUser(user);

        User result = userDao.validateCredentials("adminTest", "secret123");

        assertNotNull("Should return user for correct credentials", result);
        assertEquals("adminTest", result.getUsername());
        assertEquals("secret123", result.getPassword());
        assertTrue(result.isAdmin());
    }
}



