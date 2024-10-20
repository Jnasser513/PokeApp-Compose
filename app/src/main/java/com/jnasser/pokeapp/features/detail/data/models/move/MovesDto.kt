package com.jnasser.pokeapp.features.detail.data.models.move

import kotlinx.serialization.Serializable

@Serializable
data class MovesDto(
    val move: MoveDetailDto
)
