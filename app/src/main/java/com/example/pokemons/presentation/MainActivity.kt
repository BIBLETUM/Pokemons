package com.example.pokemons.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.pokemons.R
import com.example.pokemons.databinding.ActivityMainBinding
import com.example.pokemons.presentation.list.FragmentPokemons

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = FragmentPokemons.newInstance()
        supportFragmentManager.beginTransaction().replace(R.id.pokemons_container, fragment)
            .commit()
    }
}