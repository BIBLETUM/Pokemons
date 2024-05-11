package com.example.pokemons.domain

import javax.inject.Inject

class GetPokemonsListUseCase @Inject constructor(private val repository: PokemonRepository) {

    operator fun invoke() = repository.getPokemonsList()

}