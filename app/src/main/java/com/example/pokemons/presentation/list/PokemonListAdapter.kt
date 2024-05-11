package com.example.pokemons.presentation.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.pokemons.databinding.PokemonItemBinding
import com.example.pokemons.domain.models.Pokemon
import com.squareup.picasso.Picasso

class PokemonListAdapter : ListAdapter<Pokemon, PokemonViewHolder>(PokemonDiffCallBack()) {

    var onReachEndListener: OnReachEndListener? = null
        set(value) {
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val binding = PokemonItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PokemonViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        val pokemon = getItem(position)
        val binding = holder.binding

        with(binding) {
            TVPokemonName.text = pokemon.name
            Picasso.get().load(pokemon.sprites?.frontDefault).into(IVPokemonImage)
        }

        if (position == itemCount - 10 && onReachEndListener != null) {
            onReachEndListener?.onReachEnd()
        }
    }

    interface OnReachEndListener{
        fun onReachEnd()
    }

}