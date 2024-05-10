package com.example.pokemons.data.network.models

import com.google.gson.annotations.SerializedName

data class PokemonsListResponseDto(
    @SerializedName("results") val results: List<PokemonResultDto>?,
)