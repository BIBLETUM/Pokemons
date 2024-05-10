package com.example.pokemons.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.pokemons.R
import com.example.pokemons.presentation.list.PokemonsViewModel
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: PokemonsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as PokemonsApplication).component
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        component.inject(this)

        viewModel = ViewModelProvider(this, viewModelFactory)[PokemonsViewModel::class.java]
        viewModel.a()
    }
}