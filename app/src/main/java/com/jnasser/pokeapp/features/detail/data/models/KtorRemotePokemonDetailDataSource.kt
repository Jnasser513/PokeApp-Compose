package com.jnasser.pokeapp.features.detail.data.models

import com.jnasser.pokeapp.core.data.network.ApiConstants
import com.jnasser.pokeapp.core.data.network.get
import com.jnasser.pokeapp.core.domain.models.pokemon_detail.PokemonDetail
import com.jnasser.pokeapp.core.domain.util.error_handler.DataError
import com.jnasser.pokeapp.core.domain.util.error_handler.Result
import com.jnasser.pokeapp.core.domain.util.error_handler.map
import com.jnasser.pokeapp.features.detail.domain.RemotePokemonDetailDataSource
import io.ktor.client.HttpClient

class KtorRemotePokemonDetailDataSource(
    val ktorClient: HttpClient
): RemotePokemonDetailDataSource {

    override suspend fun getRemotePokemonDetail(pokemonName: String): Result<PokemonDetail, DataError.Network> {
        return ktorClient.get<PokemonDetailDto>(
            route = "${ApiConstants.ENDPOINT_POKEMON_DETAIL}/$pokemonName"
        ).map { it.toPokemonDetail() }
    }
}