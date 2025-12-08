package com.example.project2_group4;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.project2_group4.databinding.ActivityFavoritesBinding;

import java.util.ArrayList;

public class FavoritesActivity extends AppCompatActivity {

    private ActivityFavoritesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFavoritesBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        ArrayList<String> favoriteList =
                new ArrayList<>(FavoritesManager.getFavorites(this));


        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                favoriteList
        );


        binding.favoritesListView.setAdapter(adapter);
    }

    public static Intent landingToFavorites(Context context) {
        return new Intent(context, FavoritesActivity.class);
    }
}
