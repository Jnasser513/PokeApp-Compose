package com.jnasser.pokeapp.features.detail.data.models

import com.jnasser.pokeapp.features.detail.data.models.ability.AbilityDto
import com.jnasser.pokeapp.features.detail.data.models.held_item.HeldItemsDto
import com.jnasser.pokeapp.features.detail.data.models.move.MovesDto
import com.jnasser.pokeapp.features.detail.data.models.stat.StatsDto
import kotlinx.serialization.Serializable

@Serializable
data class PokemonDetailDto(
    val abilities: List<AbilityDto>,
    val base_experience: Int,
    val height: Int,
    val held_items: List<HeldItemsDto>,
    val id: Int,
    val moves: List<MovesDto>,
    val name: String,
    val sprites: SpritesDto,
    val stats: List<StatsDto>,
    val weight: Int
)
