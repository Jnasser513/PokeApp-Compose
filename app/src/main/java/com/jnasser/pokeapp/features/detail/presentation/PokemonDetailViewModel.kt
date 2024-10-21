package com.jnasser.pokeapp.features.detail.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jnasser.pokeapp.core.data.PreferenceManager
import com.jnasser.pokeapp.core.domain.util.PreferenceConstants
import com.jnasser.pokeapp.core.domain.util.error_handler.DataError
import com.jnasser.pokeapp.core.domain.util.error_handler.Result
import com.jnasser.pokeapp.core.presentation.ui.asUiText
import com.jnasser.pokeapp.features.detail.domain.PokemonDetailRepository
import com.jnasser.pokeapp.features.home.presentation.PokemonListEvent
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val pokemonDetailRepository: PokemonDetailRepository,
    private val preferenceManager: PreferenceManager
) : ViewModel() {

    var state by mutableStateOf(PokemonDetailViewState())
        private set

    private val eventChannel = Channel<PokemonListEvent>()
    val events = eventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            val pokemonName = async {
                preferenceManager.getData(PreferenceConstants.POKEMON_NAME)
            }.await()
            val result = pokemonName?.let { pokemonDetailRepository.getPokemonDetail(it) }
            state = when (result) {
                is Result.Error -> {
                    eventChannel.send(PokemonListEvent.Error(result.error.asUiText()))
                    state.copy(isLoading = false)
                }

                is Result.Success -> state.copy(isLoading = false, pokemonDetail = result.data)
                null -> {
                    eventChannel.send(PokemonListEvent.Error(Result.Error(DataError.Network.UNKNOWN).error.asUiText()))
                    state.copy(isLoading = false)
                }
            }
        }
    }
}