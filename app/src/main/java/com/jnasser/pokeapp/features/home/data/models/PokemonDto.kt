package com.jnasser.pokeapp.features.home.data.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonDto(
    val name: String,
    val url: String
)
