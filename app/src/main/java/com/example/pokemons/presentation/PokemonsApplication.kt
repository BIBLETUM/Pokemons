package com.example.pokemons.presentation

import android.app.Application
import com.example.pokemons.di.DaggerApplicationComponent

class PokemonsApplication: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }

}