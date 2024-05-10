package com.example.pokemons.domain

import javax.inject.Inject

class GetPokemonsListUseCase @Inject constructor(private val repository: PokemonRepository) {

    suspend operator fun invoke(offset: Int) = repository.getPokemonsList(offset)

}