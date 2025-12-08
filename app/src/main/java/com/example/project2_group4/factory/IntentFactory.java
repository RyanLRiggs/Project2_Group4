package com.example.project2_group4.factory;

import android.content.Context;
import android.content.Intent;

import com.example.project2_group4.AdminActivity;
import com.example.project2_group4.LandingPage;
import com.example.project2_group4.LoginActivity;
import com.example.project2_group4.MainActivity;
import com.example.project2_group4.SignUpActivity;

public class IntentFactory {

    public static Intent createMainActivity(Context context) {
        return new Intent(context, MainActivity.class);
    }

    public static Intent createLoginActivity(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    public static Intent createSignUpActivity(Context context) {
        return new Intent(context, SignUpActivity.class);
    }

    public static Intent createLandingPageActivity(Context context) {
        return new Intent(context, LandingPage.class);
    }

    public static Intent createAdminActivity(Context context) {
        return new Intent(context, AdminActivity.class);
    }
}
