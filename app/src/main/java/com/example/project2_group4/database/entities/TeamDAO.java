package com.example.project2_group4.database.entities;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TeamDAO {

    @Insert
    void insertTeamMember(TeamData team);

    @Delete
    void deleteTeamMember(TeamData team);

    @Query("SELECT * FROM teams WHERE userID = :userID AND teamName = :teamName")
    List<TeamData> getTeamMembers(int userID, String teamName);

    @Query("SELECT DISTINCT teamName FROM teams WHERE userID = :userID")
    List<String> getTeamName(int userID);

    @Query("DELETE FROM teams WHERE userID = :userID AND pokemonID")
    void removePokemonFromTeam(int userID, int pokemonID);

    @Query("DELETE FROM teams WHERE userID = :userID AND teamID = :teamID")
    void removeTeam(int userID, int teamID);

    @Query("SELECT COUNT(*) FROM teams WHERE userID = :userID AND teamName = :teamName")
    int getTeamSize(int userID, int teamName);

}
