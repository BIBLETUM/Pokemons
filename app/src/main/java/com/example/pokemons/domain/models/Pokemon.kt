package com.example.pokemons.domain.models

data class Pokemon(
    val name: String?,
    val id: Int,
    val height: Int?,
    val weight: Int?,
    val sprites: Sprites?,
)
