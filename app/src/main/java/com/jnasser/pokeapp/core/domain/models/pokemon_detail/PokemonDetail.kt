package com.jnasser.pokeapp.core.domain.models.pokemon_detail

import com.jnasser.pokeapp.core.domain.models.pokemon_detail.ability.Ability
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.held_item.HeldItems
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.move.Moves
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.stat.Stats

data class PokemonDetail(
    val abilities: List<Ability> = emptyList(),
    val baseExperience: Int? = null,
    val height: Int? = null,
    val heldItems: List<HeldItems> = emptyList(),
    val id: Int? = null,
    val moves: List<Moves> = emptyList(),
    val name: String = "",
    val sprites: Sprites? = null,
    val stats: List<Stats> = emptyList(),
    val weight: Int? = null
)
