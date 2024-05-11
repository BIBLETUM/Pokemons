package com.example.pokemons.presentation.list

import com.example.pokemons.domain.models.Pokemon

sealed class PokemonListState {
    data class Error(val errorMessage: String) : PokemonListState()
    data object Loading : PokemonListState()
    data class Content(val pokemonsList: List<Pokemon>) : PokemonListState()
}