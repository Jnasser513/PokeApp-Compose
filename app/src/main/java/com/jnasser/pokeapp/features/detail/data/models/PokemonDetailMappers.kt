package com.jnasser.pokeapp.features.detail.data.models

import com.jnasser.pokeapp.core.domain.models.pokemon_detail.PokemonDetail
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.Sprites
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.ability.Ability
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.ability.AbilityDetail
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.held_item.HeldItemDetail
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.held_item.HeldItems
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.move.MoveDetail
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.move.Moves
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.stat.StatDetail
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.stat.Stats
import com.jnasser.pokeapp.core.domain.models.pokemon_list.Pokemon
import com.jnasser.pokeapp.core.domain.models.pokemon_list.PokemonListResult
import com.jnasser.pokeapp.features.detail.data.models.ability.AbilityDetailDto
import com.jnasser.pokeapp.features.detail.data.models.ability.AbilityDto
import com.jnasser.pokeapp.features.detail.data.models.held_item.HeldItemDetailDto
import com.jnasser.pokeapp.features.detail.data.models.held_item.HeldItemsDto
import com.jnasser.pokeapp.features.detail.data.models.move.MoveDetailDto
import com.jnasser.pokeapp.features.detail.data.models.move.MovesDto
import com.jnasser.pokeapp.features.detail.data.models.stat.StatDetailDto
import com.jnasser.pokeapp.features.detail.data.models.stat.StatsDto

fun SpritesDto.toSprite() = Sprites(
    image = front_default
)

fun StatDetailDto.toStatDetail() = StatDetail(
    name = name
)

fun StatsDto.toStats() = Stats(
    stat = stat.toStatDetail()
)

fun MoveDetailDto.toMoveDetail() = MoveDetail(
    name = name
)

fun MovesDto.toMoves() = Moves(
    move = move.toMoveDetail()
)

fun HeldItemDetailDto.toHeldItemDetail() = HeldItemDetail(
    name = name
)

fun HeldItemsDto.toHeldItems() = HeldItems(
    item = item.toHeldItemDetail()
)

fun AbilityDetailDto.toAbilityDetail() = AbilityDetail(
    name = name
)

fun AbilityDto.toAbility() = Ability(
    ability = ability.toAbilityDetail()
)

fun PokemonDetailDto.toPokemonDetail() = PokemonDetail(
    abilities = abilities.map { it.toAbility() },
    baseExperience = base_experience,
    height = height,
    heldItems = held_items.map { it.toHeldItems() },
    id = id,
    moves = moves.map { it.toMoves() },
    name = name,
    sprites = sprites.toSprite(),
    stats = stats.map { it.toStats() },
    weight = weight
)