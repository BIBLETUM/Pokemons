package com.example.pokemons.data

import com.example.pokemons.data.network.ApiService
import com.example.pokemons.domain.PokemonRepository
import com.example.pokemons.domain.models.Pokemon
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: PokemonMapper,
): PokemonRepository {
    override suspend fun getPokemonsList(offset: Int): List<Pokemon> {
        val listPokemonResultDto = apiService.getPokemonsList(offset).results
        listPokemonResultDto?.let {
            return listPokemonResultDto
                .map { mapper.mapUrlStringToId(it.url?:"0") }
                .map { getPokemon(it) }
        }
        return emptyList()
    }

    override suspend fun getPokemon(id: Int): Pokemon {
        return mapper.mapPokemonResponseDtoToPokemon(apiService.getPokemon(id))
    }
}