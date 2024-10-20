package com.jnasser.pokeapp.features.home.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonListResultDto(
    val results: List<PokemonDto>
)
