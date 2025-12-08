package com.example.project2_group4;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoritesManager {

    private static final String PREF_NAME = "favorites_preferences";
    private static final String KEY_FAVORITES = "favorite_pokemon";

    public static void addFavorite(Context context, String pokemonName) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);

        Set<String> favorites = prefs.getStringSet(KEY_FAVORITES, new HashSet<>());
        favorites = new HashSet<>(favorites); //new copy made w this

        favorites.add(pokemonName);

        prefs.edit().putStringSet(KEY_FAVORITES, favorites).apply();
    }

    public static Set<String> getFavorites(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return prefs.getStringSet(KEY_FAVORITES, new HashSet<>());
    }
}
