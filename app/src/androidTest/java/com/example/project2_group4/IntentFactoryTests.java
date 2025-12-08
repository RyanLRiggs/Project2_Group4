package com.example.project2_group4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.content.Intent;

import androidx.test.core.app.ApplicationProvider;

import com.example.project2_group4.factory.IntentFactory;

import org.junit.Before;
import org.junit.Test;

public class IntentFactoryTests {

    private Context context;

    @Before
    public void setup() {
        context = ApplicationProvider.getApplicationContext();
    }

    @Test
    public void testCreateMainActivity() {
        Intent intent = IntentFactory.createMainActivity(context);
        assertNotNull(intent);
        assertEquals(MainActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void testCreateLoginActivity() {
        Intent intent = IntentFactory.createLoginActivity(context);
        assertNotNull(intent);
        assertNotNull(LoginActivity.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void testCreateSignUpActivityIntent() {
        Intent intent = IntentFactory.createSignUpActivity(context);
        assertNotNull(intent);
        assertEquals(SignUpActivity.class.getName(),
                intent.getComponent().getClassName());
    }

    @Test
    public void testCreateLandingPageIntent() {
        Intent intent = IntentFactory.createLandingPageActivity(context);
        assertNotNull(intent);
        assertEquals(LandingPage.class.getName(), intent.getComponent().getClassName());
    }

    @Test
    public void testCreateAdminActivityIntent() {
        Intent intent = IntentFactory.createAdminActivity(context);
        assertNotNull(intent);
        assertEquals(AdminActivity.class.getName(), intent.getComponent().getClassName());
    }

}
