package com.example.pokemons.presentation.profile

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.pokemons.R
import com.example.pokemons.databinding.FragmentFragmentPokemonProfileBinding
import com.example.pokemons.domain.models.Pokemon
import com.example.pokemons.presentation.PokemonsApplication
import com.example.pokemons.presentation.ViewModelFactory
import com.example.pokemons.presentation.list.PokemonsViewModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentPokemonProfile : Fragment() {

    private var _binding: FragmentFragmentPokemonProfileBinding? = null
    private val binding: FragmentFragmentPokemonProfileBinding
        get() = _binding ?: throw RuntimeException("FragmentFragmentPokemonProfileBinding == null")

    private lateinit var viewModel: PokemonProfileViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as PokemonsApplication).component
    }

    private var pokemonId: Int = INITIAL_POKEMON_ID
    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFragmentPokemonProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this, viewModelFactory)[PokemonProfileViewModel::class.java]
        viewModel.loadPokemon(pokemonId)
        observeViewModel()
    }

    private fun observeViewModel(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect{
                    when(it) {
                        is PokemonProfileState.Loading -> {
                            binding.progress.isVisible = true
                        }

                        is PokemonProfileState.Error -> {
                            binding.progress.isVisible = false
                            Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT).show()
                        }

                        is PokemonProfileState.Content -> {
                            binding.progress.isVisible = false
                            insertData(it.pokemon)
                        }
                    }
                }
            }
        }
    }

    private fun insertData(pokemon: Pokemon){
        with(binding) {
            TVPokemonName.text = String.format(getString(R.string.name), pokemon.name)
            TVPokemonWeight.text = String.format(getString(R.string.weight), pokemon.weight)
            TVPokemonHeight.text = String.format(getString(R.string.height), pokemon.height)
            TVPokemonHp.text = String.format(getString(R.string.hp), pokemon.hp)
            TVPokemonAttack.text = String.format(getString(R.string.attack), pokemon.attack)
            TVPokemonDefense.text = String.format(getString(R.string.defence), pokemon.defence)
            TVPokemonSpecialAttack.text = String.format(getString(R.string.spa_attack), pokemon.specialAttack)
            TVPokemonSpecialDefence.text = String.format(getString(R.string.spa_defence), pokemon.specialDefence)
            TVPokemonSpeed.text = String.format(getString(R.string.speed), pokemon.speed)

            Picasso.get().load(pokemon.sprites?.frontDefault).into(IVPokemonImage)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseParams() {
        val args = requireArguments()
        if (!args.containsKey(POKEMON_ID_ARGUMENT)) throw RuntimeException("Param POKEMON_ID_ARGUMENT mode is absent")
        val id = args.getInt(POKEMON_ID_ARGUMENT)
        if (id <= 0) throw RuntimeException("Unknown POKEMON_ID_ARGUMENT: $id")
        pokemonId = id
    }

    companion object {
        fun newInstance(id: Int) = FragmentPokemonProfile().apply {
            arguments = Bundle().apply {
                putInt(POKEMON_ID_ARGUMENT, id)
            }
        }

        const val POKEMON_ID_ARGUMENT = "POKEMON_ID_ARGUMENT"
        const val INITIAL_POKEMON_ID = -1
    }

}