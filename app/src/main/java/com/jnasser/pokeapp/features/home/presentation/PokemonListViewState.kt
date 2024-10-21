package com.jnasser.pokeapp.features.home.presentation

import com.jnasser.pokeapp.core.domain.models.pokemon_list.Pokemon

data class PokemonListViewState(
    val isLoading: Boolean = true,
    val pokemonList: List<Pokemon> = emptyList()
)
