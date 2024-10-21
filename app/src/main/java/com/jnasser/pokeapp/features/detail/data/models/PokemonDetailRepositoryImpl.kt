package com.jnasser.pokeapp.features.detail.data.models

import com.jnasser.pokeapp.core.domain.models.pokemon_detail.PokemonDetail
import com.jnasser.pokeapp.core.domain.util.error_handler.DataError
import com.jnasser.pokeapp.core.domain.util.error_handler.Result
import com.jnasser.pokeapp.features.detail.domain.PokemonDetailRepository

class PokemonDetailRepositoryImpl(
    private val ktorRemotePokemonDetailDataSource: KtorRemotePokemonDetailDataSource
): PokemonDetailRepository {

    override suspend fun getPokemonDetail(name: String): Result<PokemonDetail, DataError.Network> {
        return when(val result = ktorRemotePokemonDetailDataSource.getRemotePokemonDetail(name)) {
            is Result.Error -> Result.Error(result.error)
            is Result.Success -> Result.Success(result.data)
        }
    }
}