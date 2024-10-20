package com.jnasser.pokeapp.features.detail.presentation.composables

sealed interface PokemonDetailAction {
    data object OnReturn: PokemonDetailAction
}