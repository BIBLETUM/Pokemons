package com.example.pokemons.data.network.models

import com.google.gson.annotations.SerializedName

data class Stats(
    @SerializedName("base_stat") val baseStat: Int?,
    @SerializedName("stat") val stat: Stat?,
)
