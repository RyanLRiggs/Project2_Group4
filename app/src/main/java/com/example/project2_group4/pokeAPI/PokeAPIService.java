package com.example.project2_group4.pokeAPI;


import retrofit2.http.GET;
import retrofit2.Call;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PokeAPIService {
    @GET("pokemon")
    Call<PokemonList> getPokemonList(
            @Query("limit") int limit,
            @Query("offset") int offset
    );

    @GET("pokemon/{name}")
    Call<PokemonDetail> getPokemonDetail(@Path("name") String name);

}
