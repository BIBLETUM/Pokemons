package com.example.pokemons.presentation.profile

import com.example.pokemons.domain.models.Pokemon

sealed class PokemonProfileState {
    data class Error(val errorMessage: String) : PokemonProfileState()
    data object Loading : PokemonProfileState()
    data class Content(val pokemon: Pokemon) : PokemonProfileState()
}