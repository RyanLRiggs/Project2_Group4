package Retrofit

import PokiAPI.Pokedex
import io.reactivex.Observable
import retrofit2.http.GET

interface IPokemonlList {

    @GET("pokedex.json")
    fun getPokemonList(): Observable<Pokedex>
}