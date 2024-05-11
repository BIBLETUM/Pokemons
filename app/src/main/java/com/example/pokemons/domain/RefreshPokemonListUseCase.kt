package com.example.pokemons.domain

import javax.inject.Inject

class RefreshPokemonListUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke() = repository.refreshPokemonList()

}