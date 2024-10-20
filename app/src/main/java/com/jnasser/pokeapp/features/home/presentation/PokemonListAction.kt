package com.jnasser.pokeapp.features.home.presentation

sealed interface PokemonListAction {
    data class OnPokemonClick(val pokemonName: String): PokemonListAction
}