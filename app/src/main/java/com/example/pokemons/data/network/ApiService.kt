package com.example.pokemons.data.network

import com.example.pokemons.data.network.models.PokemonResponseDto
import com.example.pokemons.data.network.models.PokemonsListResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("pokemon?limit=50")
    suspend fun getPokemonsList(@Query("offset") offset: Int): PokemonsListResponseDto

    @GET("pokemon/{id}/")
    suspend fun getPokemon(@Path("id") id: Int): PokemonResponseDto

}