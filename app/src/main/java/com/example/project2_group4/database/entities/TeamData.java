package com.example.project2_group4.database.entities;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import static androidx.room.ForeignKey.CASCADE;

@Entity(
        tableName = "teams",
        foreignKeys = {
                @ForeignKey(
                        entity = User.class,
                        parentColumns = "userID",
                        childColumns = "userID",
                        onDelete = CASCADE
                )
        },
        indices = {
                @Index("userID"),
                @Index(value = {"userID", "pokemonID"}, unique = true)
        }
)
public class TeamData {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int userID;
    private int pokemonID;
    private String pokemonName;

    public TeamData(int userID, int pokemonID, String pokemonName) {
        this.userID = userID;
        this.pokemonID = pokemonID;
        this.pokemonName = pokemonName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getPokemonID() {
        return pokemonID;
    }

    public void setPokemonID(int pokemonID) {
        this.pokemonID = pokemonID;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }
}

