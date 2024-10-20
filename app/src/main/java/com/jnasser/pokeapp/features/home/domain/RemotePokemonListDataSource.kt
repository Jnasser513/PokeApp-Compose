package com.jnasser.pokeapp.features.home.domain

import com.jnasser.pokeapp.core.domain.models.PokemonListResult
import com.jnasser.pokeapp.core.domain.util.DataError
import com.jnasser.pokeapp.core.domain.util.Result

interface RemotePokemonListDataSource {

    suspend fun getRemotePokemonList(offset: Int, limit: Int): Result<PokemonListResult, DataError.Network>
}