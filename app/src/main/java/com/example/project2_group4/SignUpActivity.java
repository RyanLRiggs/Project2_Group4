package com.example.project2_group4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2_group4.databinding.ActivitySignUpBinding;


public class SignUpActivity extends AppCompatActivity {

    private ActivitySignUpBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        binding.SignUpSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.SignUpToMain(getApplicationContext());
                startActivity(intent);

            }
        });
    }

    public static Intent mainToSignUp(Context context){
        Intent intent = new Intent(context, SignUpActivity.class);
        return intent;
    }
}