package com.jnasser.pokeapp.features.home.data.models

import com.jnasser.pokeapp.core.domain.models.Pokemon
import com.jnasser.pokeapp.core.domain.models.PokemonListResult

fun PokemonDto.toPokemon() = Pokemon(
    name = name,
    url = url
)

fun PokemonListResultDto.toPokemonListResult() = PokemonListResult(
    results = results.map { it.toPokemon() }
)