package com.example.pokemons.presentation.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
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

    private val dada: MutableLiveData<Boolean> = MutableLiveData<Boolean>(false)

    init {

    }

    fun a() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                dada.postValue(false)
                try {
                    Log.d("a", getPokemonUseCase.invoke(50).toString())
                } catch (e: Exception) {

                }
            }
        }
    }

}