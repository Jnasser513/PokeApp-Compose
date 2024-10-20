package com.jnasser.pokeapp.features.home.domain

import com.jnasser.pokeapp.core.domain.models.pokemon_list.PokemonListResult
import com.jnasser.pokeapp.core.domain.util.error_handler.DataError
import com.jnasser.pokeapp.core.domain.util.error_handler.Result

interface RemotePokemonListDataSource {

    suspend fun getRemotePokemonList(offset: Int, limit: Int): Result<PokemonListResult, DataError.Network>
}