package Retrofit

import retrofit2.http.GET
import io.reactivex.Observable

interface IPokemonlList {
    @get:GET("pokedex.json")
    val listpokemon:Obervable<pokedex>
}