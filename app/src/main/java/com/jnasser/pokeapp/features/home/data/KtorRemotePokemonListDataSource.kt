package com.jnasser.pokeapp.features.home.data

import com.jnasser.pokeapp.core.data.network.ApiConstants
import com.jnasser.pokeapp.core.data.network.get
import com.jnasser.pokeapp.core.domain.models.pokemon_list.PokemonListResult
import com.jnasser.pokeapp.core.domain.util.DataError
import com.jnasser.pokeapp.core.domain.util.map
import com.jnasser.pokeapp.features.home.data.models.PokemonListResultDto
import com.jnasser.pokeapp.features.home.data.models.toPokemonListResult
import com.jnasser.pokeapp.features.home.domain.RemotePokemonListDataSource
import io.ktor.client.HttpClient

class KtorRemotePokemonListDataSource(
    val ktorClient: HttpClient
): RemotePokemonListDataSource {

    override suspend fun getRemotePokemonList(offset: Int, limit: Int): com.jnasser.pokeapp.core.domain.util.Result<PokemonListResult, DataError.Network> {
        return ktorClient.get<PokemonListResultDto>(
            route = ApiConstants.ENDPOINT_POKEMON_LIST,
            queryParameters = mapOf(
                "offset" to offset,
                "limit" to limit
            )
        ).map { it.toPokemonListResult() }
    }
}