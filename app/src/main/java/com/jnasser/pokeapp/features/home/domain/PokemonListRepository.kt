package com.jnasser.pokeapp.features.home.domain

import com.jnasser.pokeapp.core.domain.models.PokemonListResult
import com.jnasser.pokeapp.core.domain.util.DataError
import com.jnasser.pokeapp.core.domain.util.Result

interface PokemonListRepository {

    suspend fun getPokemonList(
        offset: Int,
        limit: Int
    ): Result<PokemonListResult, DataError.Network>
}