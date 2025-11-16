package com.example.project2_group4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


import com.example.project2_group4.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {


    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.MainLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = LoginActivity.mainToLogin(getApplicationContext());
                startActivity(intent);
            }
        });

        binding.MainSignupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SignUpActivity.mainToSignUp(getApplicationContext());
                startActivity(intent);
            }
        });




    }

    public static Intent SignUpToMain(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        return intent;
    }
}