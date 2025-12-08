package com.example.project2_group4.database.entities;


import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "teams",
        foreignKeys = {
                @ForeignKey(entity = User.class, parentColumns = "userID", childColumns = "userID", onDelete = ForeignKey.CASCADE),
                @ForeignKey(entity = PokemonData.class, parentColumns = "pokemonID", childColumns = "pokemonID", onDelete = ForeignKey.CASCADE)
        })
public class TeamData {

    @PrimaryKey(autoGenerate = true)
    private int teamID;
    private int pokemonID;
    private int userID;
    private String teamName;
    private int positionInTeam;

    public TeamData(int pokemonID, int userID, String teamName, int positionInTeam) {
        this.pokemonID = pokemonID;
        this.userID = userID;
        this.teamName = teamName;
        this.positionInTeam = positionInTeam;
    }

    public int getTeamID() {
        return teamID;
    }

    public void setTeamID(int teamID) {
        this.teamID = teamID;
    }

    public int getPokemonID() {
        return pokemonID;
    }

    public void setPokemonID(int pokemonID) {
        this.pokemonID = pokemonID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPositionInTeam() {
        return positionInTeam;
    }

    public void setPositionInTeam(int positionInTeam) {
        this.positionInTeam = positionInTeam;
    }
}
