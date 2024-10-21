package com.jnasser.pokeapp.features.detail.presentation

import com.jnasser.pokeapp.core.presentation.ui.UiText

interface PokemonDetailEvent {
    data class Error(val error: UiText): PokemonDetailEvent
}