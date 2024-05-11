package com.example.pokemons.data

import com.example.pokemons.data.network.models.PokemonResponseDto
import com.example.pokemons.data.network.models.SpritesDto
import com.example.pokemons.domain.models.Pokemon
import com.example.pokemons.domain.models.Sprites
import javax.inject.Inject

class PokemonMapper @Inject constructor() {

    fun mapPokemonResponseDtoToPokemon(pokemonDto: PokemonResponseDto) = Pokemon(
        name = pokemonDto.name,
        id = pokemonDto.id,
        height = pokemonDto.height,
        weight = pokemonDto.weight,
        sprites = mapSpritesDtoToSpritesModel(pokemonDto.sprites),
        hp = getBaseStatFromStats(pokemonDto, "hp"),
        attack = getBaseStatFromStats(pokemonDto, "attack"),
        defence = getBaseStatFromStats(pokemonDto, "defense"),
        specialAttack = getBaseStatFromStats(pokemonDto, "special-attack"),
        specialDefence = getBaseStatFromStats(pokemonDto, "special-defense"),
        speed = getBaseStatFromStats(pokemonDto, "speed"),
        )

    private fun getBaseStatFromStats(pokemonDto: PokemonResponseDto, statName: String): Int {
        val temp = pokemonDto.stats?.filter { it.stat?.name == statName }
        return temp?.get(0)?.baseStat ?: 0
    }

    private fun mapSpritesDtoToSpritesModel(spritesDto: SpritesDto?) = Sprites(
        backDefault = spritesDto?.backDefault,
        backFemale = spritesDto?.backFemale,
        backShiny = spritesDto?.backShiny,
        backShinyFemale = spritesDto?.backShinyFemale,
        frontDefault = spritesDto?.frontDefault,
        frontFemale = spritesDto?.frontFemale,
        frontShiny = spritesDto?.frontShiny,
        frontShinyFemale = spritesDto?.frontShinyFemale,
    )

    fun mapUrlStringToId(url: String): Int {
        val regex = Regex("""/(\d+)/""")
        val matchResult = regex.find(url)

        return (matchResult?.groupValues?.get(1)?.toInt() ?: -1)
    }

}