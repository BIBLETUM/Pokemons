package com.example.pokemons.di

import com.example.pokemons.data.PokemonRepositoryImpl
import com.example.pokemons.data.network.ApiFactory
import com.example.pokemons.data.network.ApiService
import com.example.pokemons.domain.PokemonRepository
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
interface ModuleData {

    @Binds
    @ApplicationScope
    fun bindPokemonRepository(impl: PokemonRepositoryImpl): PokemonRepository

    companion object {
        @Provides
        @ApplicationScope
        fun provideApiService(): ApiService {
            return ApiFactory.apiService
        }
    }


}