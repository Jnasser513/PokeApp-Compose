package com.jnasser.pokeapp.features.detail.presentation

import com.jnasser.pokeapp.core.domain.models.pokemon_detail.PokemonDetail

data class PokemonDetailViewState(
    val isLoading: Boolean = true,
    val pokemonDetail: PokemonDetail = PokemonDetail()
)
