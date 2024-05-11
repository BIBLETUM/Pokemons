package com.example.pokemons.presentation.list

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
import com.example.pokemons.databinding.FragmentFragmentPokemonsBinding
import com.example.pokemons.presentation.PokemonsApplication
import com.example.pokemons.presentation.ViewModelFactory
import kotlinx.coroutines.launch
import javax.inject.Inject

class FragmentPokemons : Fragment() {

    private var _binding: FragmentFragmentPokemonsBinding? = null
    private val binding: FragmentFragmentPokemonsBinding
        get() = _binding ?: throw RuntimeException("FragmentFragmentPokemonsBinding == null")

    private lateinit var viewModel: PokemonsViewModel

    private lateinit var adapter: PokemonListAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as PokemonsApplication).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFragmentPokemonsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = PokemonListAdapter()
        binding.RVPokemons.adapter = adapter
        viewModel = ViewModelProvider(this, viewModelFactory)[PokemonsViewModel::class.java]
        setUpListeners()
        subScribeOnViewModel()
    }

    private fun setUpListeners() {
        adapter.onReachEndListener = object : PokemonListAdapter.OnReachEndListener {
            override fun onReachEnd() {
                viewModel.refreshList()
            }
        }
    }

    private fun subScribeOnViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.state.collect {
                    when (it) {
                        is PokemonListState.Loading -> {
                            binding.progress.isVisible = true
                        }

                        is PokemonListState.Error -> {
                            binding.progress.isVisible = false
                            Toast.makeText(requireContext(), it.errorMessage, Toast.LENGTH_SHORT)
                                .show()
                        }

                        is PokemonListState.Content -> {
                            Log.d("aboba", (it.pokemonsList.groupingBy { it }.eachCount().filter { it.value > 1 }.toString()))
                            binding.progress.isVisible = false
                            adapter.submitList(it.pokemonsList)
                        }
                    }
                }
            }
        }
    }

    companion object {
        fun newInstance() = FragmentPokemons()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}