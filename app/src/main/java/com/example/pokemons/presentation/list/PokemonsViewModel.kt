package com.example.pokemons.presentation.list

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokemons.domain.GetPokemonUseCase
import com.example.pokemons.domain.GetPokemonsListUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PokemonsViewModel @Inject constructor(
    private val getPokemonsListUseCase: GetPokemonsListUseCase,
    private val getPokemonUseCase: GetPokemonUseCase,
) : ViewModel() {

    init {

    }

    fun a (){
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                Log.d("a", getPokemonUseCase.invoke(40).toString())
            }
        }
    }

}