package com.example.pokemons.data.network.models

import com.google.gson.annotations.SerializedName

data class PokemonResponseDto(
    @SerializedName("height") val height: Int?,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String?,
    @SerializedName("sprites") val sprites: SpritesDto?,
    @SerializedName("weight") val weight: Int?,
    @SerializedName("stats") val stats: List<Stats>?,
)
