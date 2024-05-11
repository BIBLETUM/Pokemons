package com.example.pokemons.data

import com.example.pokemons.data.network.ApiService
import com.example.pokemons.domain.PokemonRepository
import com.example.pokemons.domain.models.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class PokemonRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val mapper: PokemonMapper,
) : PokemonRepository {

    private val pokemonsList = mutableListOf<Pokemon>()
    private val refreshEvents = MutableSharedFlow<Unit>()
    private var currentOffset: Int = 0

    override fun getPokemonsList(): Flow<List<Pokemon>> = flow {
        loadPokemonsList()
        emit(pokemonsList.toList())
        refreshEvents.collect {
            loadPokemonsList()
            emit(pokemonsList.toList())
        }
    }

    private suspend fun loadPokemonsList() {
        val loadedPokemonListResult = apiService.getPokemonsList(currentOffset).results
            ?: throw RuntimeException("Api returned null")
        val loadedPokemonId = loadedPokemonListResult.map { mapper.mapUrlStringToId(it.url ?: "0") }
        val loadedPokemonList = loadedPokemonId.map { getPokemon(it) }
        currentOffset += PAGE_SIZE
        pokemonsList.addAll(loadedPokemonList)
    }

    override suspend fun refreshPokemonList() {
        refreshEvents.emit(Unit)
    }

    override suspend fun getPokemon(id: Int): Pokemon {
        if (id <= 0) throw RuntimeException("PokemonId < 0 exception")
        return mapper.mapPokemonResponseDtoToPokemon(apiService.getPokemon(id))
    }

    companion object {

        const val PAGE_SIZE = 50

    }

}