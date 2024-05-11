package com.example.pokemons.domain

import com.example.pokemons.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun refreshPokemonList()

    fun getPokemonsList(): Flow<List<Pokemon>>

    suspend fun getPokemon(id: Int): Pokemon

}