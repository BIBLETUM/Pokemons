package com.example.pokemons.presentation.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemons.domain.GetPokemonsListUseCase
import com.example.pokemons.domain.RefreshPokemonListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonsViewModel @Inject constructor(
    private val getPokemonsListUseCase: GetPokemonsListUseCase,
    private val refreshPokemonListUseCase: RefreshPokemonListUseCase,
) : ViewModel() {

    private val loadingFlow = MutableSharedFlow<PokemonListState>()

    val state: Flow<PokemonListState> = getPokemonsListUseCase.invoke()
        .filter { it.isNotEmpty() }
        .map { PokemonListState.Content(it) as PokemonListState }
        .onStart { emit(PokemonListState.Loading) }
        .mergeWith(loadingFlow)
        .catch { emit(PokemonListState.Error(it.toString())) }

    init {
        refreshList()
    }

    fun refreshList() {
        viewModelScope.launch {
            loadingFlow.emit(PokemonListState.Loading)
            refreshPokemonListUseCase.invoke()
        }
    }

    private fun <T> Flow<T>.mergeWith(another: Flow<T>): Flow<T> {
        return merge(this, another)
    }
}