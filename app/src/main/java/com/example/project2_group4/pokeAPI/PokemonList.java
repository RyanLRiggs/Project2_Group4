package com.example.project2_group4.pokeAPI;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PokemonList {

    @SerializedName("results")
    private List<PokemonBasic> results;

    public List<PokemonBasic> getResults() {
        return results;
    }

    public static class PokemonBasic {
        @SerializedName("name")
        private String name;

        @SerializedName("url")
        private String url;

        public String getName() {
            return name;
        }

        public String getUrl() {
            return url;
        }
    }
}
