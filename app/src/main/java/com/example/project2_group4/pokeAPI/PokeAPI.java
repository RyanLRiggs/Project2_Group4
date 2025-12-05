package com.example.project2_group4.pokeAPI;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokeAPI {

    private static final String BASE_URL = "https://pokeapi.co/api/v2/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public static PokeAPIService getPokeApiService() {
        return getClient().create(PokeAPIService.class);
    }
}
