package com.jnasser.pokeapp.features.home.data.models

import com.jnasser.pokeapp.core.domain.models.pokemon_list.Pokemon
import com.jnasser.pokeapp.core.domain.models.pokemon_list.PokemonListResult

fun PokemonDto.toPokemon() = Pokemon(
    name = name,
    url = url
)

fun PokemonListResultDto.toPokemonListResult() = PokemonListResult(
    results = results.map { it.toPokemon() }
)