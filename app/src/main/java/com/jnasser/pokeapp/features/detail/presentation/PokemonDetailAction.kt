package com.jnasser.pokeapp.features.detail.presentation

sealed interface PokemonDetailAction {
    data object OnReturn: PokemonDetailAction
}