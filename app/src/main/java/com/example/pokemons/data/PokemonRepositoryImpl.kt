package com.example.pokemons.data

import com.example.pokemons.data.network.ApiService
import com.example.pokemons.domain.PokemonRepository
import com.example.pokemons.domain.models.Pokemon
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: PokemonMapper,
) : PokemonRepository {
    override suspend fun getPokemonsList(offset: Int): List<Pokemon> {
        val listPokemonResultDto = apiService.getPokemonsList(offset).results
            ?: throw RuntimeException("Api returned null")

        return listPokemonResultDto
            .map { mapper.mapUrlStringToId(it.url ?: "0") }
            .map { getPokemon(it) }
    }

    override suspend fun getPokemon(id: Int): Pokemon {
        if (id <= 0) throw RuntimeException("PokemonId < 0 exception")
        return mapper.mapPokemonResponseDtoToPokemon(apiService.getPokemon(id))
    }
}