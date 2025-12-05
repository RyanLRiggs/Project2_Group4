package com.example.project2_group4.database.entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface PokemonDAO {

    @Insert
    void insertPokemon(Pokemon pokemon);

    @Delete
    void deletePokemon(Pokemon pokemon);

    @Query("SELECT * FROM pokemon WHERE pokemonID = :pokemonID AND userID = :userID LIMIT 1")
    Pokemon getPokemonByIDAndUser(int pokemonID, int userID);

    @Query("DELETE FROM pokemon WHERE pokemonId = :pokemonId AND userId = :userId")
    void deletePokemonByIdAndUser(int pokemonId, int userId);

    @Query("SELECT COUNT(*) FROM pokemon WHERE pokemonId = :pokemonId AND userId = :userId")
    int isPokemonSaved(int pokemonId, int userId);
}
