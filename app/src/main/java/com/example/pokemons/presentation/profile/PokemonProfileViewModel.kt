package com.example.pokemons.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemons.domain.GetPokemonUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class PokemonProfileViewModel @Inject constructor(
    private val getPokemonUseCase: GetPokemonUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<PokemonProfileState>(PokemonProfileState.Loading)
    val state = _state.asStateFlow()

    fun loadPokemon(id: Int) {
        viewModelScope.launch {
            try {
                _state.value = PokemonProfileState.Content(getPokemonUseCase.invoke(id))
            } catch (e: Exception) {
                _state.value = PokemonProfileState.Error(e.toString())
            }
        }
    }

}