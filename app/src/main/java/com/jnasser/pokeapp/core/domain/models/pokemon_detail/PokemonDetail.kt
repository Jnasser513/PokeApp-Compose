package com.jnasser.pokeapp.core.domain.models.pokemon_detail

import com.jnasser.pokeapp.core.domain.models.pokemon_detail.ability.Ability
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.held_item.HeldItems
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.move.Moves
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.stat.Stats

data class PokemonDetail(
    val abilities: List<Ability>,
    val baseExperience: Int,
    val height: Int,
    val heldItems: List<HeldItems>,
    val id: Int,
    val moves: List<Moves>,
    val name: String,
    val sprites: Sprites,
    val stats: List<Stats>,
    val weight: Int
)
