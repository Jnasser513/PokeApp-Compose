package com.jnasser.pokeapp.features.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnasser.pokeapp.core.data.PreferenceManager
import com.jnasser.pokeapp.core.domain.util.PreferenceConstants
import com.jnasser.pokeapp.core.domain.util.error_handler.map
import com.jnasser.pokeapp.features.home.domain.PokemonListRepository
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import com.jnasser.pokeapp.core.domain.util.error_handler.Result
import com.jnasser.pokeapp.core.presentation.ui.asUiText

class PokemonListViewModel(
    private val pokemonListRepository: PokemonListRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    var state by mutableStateOf(PokemonListViewState())
        private set

    private val eventChannel = Channel<PokemonListEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            val result = pokemonListRepository.getPokemonList(0, 20).map { it.results }
            state = when (result) {
                is Result.Error -> {
                    eventChannel.send(PokemonListEvent.Error(result.error.asUiText()))
                    state.copy(isLoading = false)
                }

                is Result.Success -> state.copy(isLoading = false, pokemonList = result.data)
            }
        }
    }

    fun onAction(action: PokemonListAction) {
        when (action) {
            is PokemonListAction.OnPokemonClick -> preferenceManager.setData(
                PreferenceConstants.POKEMON_NAME,
                action.pokemonName
            )
        }
    }
}