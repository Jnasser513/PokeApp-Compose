package com.jnasser.pokeapp.features.detail.data.models.held_item

import kotlinx.serialization.Serializable

@Serializable
data class HeldItemsDto(
    val item: HeldItemDetailDto
)
