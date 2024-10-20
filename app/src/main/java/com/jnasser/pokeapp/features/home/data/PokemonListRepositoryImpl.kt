package com.jnasser.pokeapp.features.home.data

import com.jnasser.pokeapp.core.domain.models.pokemon_list.PokemonListResult
import com.jnasser.pokeapp.core.domain.util.DataError
import com.jnasser.pokeapp.features.home.domain.PokemonListRepository
import com.jnasser.pokeapp.core.domain.util.Result

class PokemonListRepositoryImpl(
    private val ktorRemotePokemonListDataSource: KtorRemotePokemonListDataSource
): PokemonListRepository {

    override suspend fun getPokemonList(
        offset: Int,
        limit: Int
    ): Result<PokemonListResult, DataError.Network> {
        return when(val result = ktorRemotePokemonListDataSource.getRemotePokemonList(offset, limit)) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(result.data)
        }
    }
}