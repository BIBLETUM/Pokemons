package com.example.pokemons.domain

import com.example.pokemons.domain.models.Pokemon

interface PokemonRepository {

    suspend fun getPokemonsList(offset: Int): List<Pokemon>

    suspend fun getPokemon(id: Int): Pokemon

}