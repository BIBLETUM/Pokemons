package com.example.pokemons.domain

import javax.inject.Inject

class GetPokemonUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(id: Int) = repository.getPokemon(id)

}