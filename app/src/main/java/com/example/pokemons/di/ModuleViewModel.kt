package com.example.pokemons.di

import androidx.lifecycle.ViewModel
import com.example.pokemons.presentation.profile.PokemonProfileViewModel
import com.example.pokemons.presentation.list.PokemonsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ModuleViewModel {

    @IntoMap
    @ViewModelKey(PokemonsViewModel::class)
    @Binds
    fun bindPokemonsViewModel(impl: PokemonsViewModel): ViewModel

    @IntoMap
    @ViewModelKey(PokemonProfileViewModel::class)
    @Binds
    fun bindPokemonProfileViewModel(impl: PokemonProfileViewModel): ViewModel

}