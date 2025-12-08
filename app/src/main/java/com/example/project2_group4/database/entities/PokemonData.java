package com.example.project2_group4.database.entities;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Objects;

@Entity(tableName = "pokemon")
public class PokemonData {

    @PrimaryKey
    private int pokemonID;
    private String name;
    private String types;
    private String spriteURL;
    private int userID;

    public PokemonData(int pokemonID, String name, String types, String spriteURL, int userID) {
        this.pokemonID = pokemonID;
        this.name = name;
        this.types = types;
        this.spriteURL = spriteURL;
        this.userID = userID;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        PokemonData pokemon = (PokemonData) o;
        return pokemonID == pokemon.pokemonID && userID == pokemon.userID && Objects.equals(name, pokemon.name) && Objects.equals(types, pokemon.types) && Objects.equals(spriteURL, pokemon.spriteURL);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pokemonID, name, types, spriteURL, userID);
    }

    public int getPokemonID() {
        return pokemonID;
    }

    public void setPokemonID(int pokemonID) {
        this.pokemonID = pokemonID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getSpriteURL() {
        return spriteURL;
    }

    public void setSpriteURL(String spriteURL) {
        this.spriteURL = spriteURL;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }
}
