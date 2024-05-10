package com.example.pokemons.data.network.models

import com.google.gson.annotations.SerializedName

data class Stat(
    @SerializedName("name") val name: String?,
)