package com.example.pokemons.presentation.list

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pokemons.R
import com.example.pokemons.presentation.PokemonsApplication
import com.example.pokemons.presentation.ViewModelFactory
import javax.inject.Inject

class FragmentPokemons : Fragment() {

    private var _binding: FragmentPokemons? = null
    private val binding: FragmentPokemons
        get() = _binding ?: throw RuntimeException("FragmentShopItemBinding == null")

    private lateinit var viewModel: PokemonsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as PokemonsApplication).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_fragment_pokemons, container, false)
    }

    companion object {
        fun newInstance() = FragmentPokemons()
    }

}