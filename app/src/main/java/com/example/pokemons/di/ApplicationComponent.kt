package com.example.pokemons.di

import android.app.Application
import com.example.pokemons.presentation.profile.FragmentPokemonProfile
import com.example.pokemons.presentation.list.FragmentPokemons
import com.example.pokemons.presentation.MainActivity
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [ModuleData::class, ModuleViewModel::class])
interface ApplicationComponent {

    fun inject(fragment: FragmentPokemons)

    fun inject(fragment: FragmentPokemonProfile)

    fun inject(activity: MainActivity)

    @Component.Factory
    interface ApplicationComponentFactory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent

    }
}