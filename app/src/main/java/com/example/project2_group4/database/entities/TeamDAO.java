package com.example.project2_group4.database.entities;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TeamDAO {

    @Insert
    void insertTeamMember(TeamData teamMember);

    @Delete
    void deleteTeamMember(TeamData teamMember);

    @Query("SELECT * FROM teams WHERE userID = :userId")
    List<TeamData> getTeamForUser(int userId);

    @Query("SELECT pokemonName FROM teams WHERE userID = :userId")
    List<String> getTeamNamesForUser(int userId);

    @Query("SELECT COUNT(*) FROM teams WHERE userID = :userId AND pokemonID = :pokemonId")
    int isPokemonInTeam(int userId, int pokemonId);

    @Query("DELETE FROM teams WHERE userID = :userId AND pokemonID = :pokemonId")
    void removePokemonFromTeam(int userId, int pokemonId);
}
