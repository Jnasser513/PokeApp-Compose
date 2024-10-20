package com.jnasser.pokeapp.features.home.presentation

import com.jnasser.pokeapp.core.presentation.ui.UiText

sealed interface PokemonListEvent {
    data class Error(val error: UiText): PokemonListEvent
}