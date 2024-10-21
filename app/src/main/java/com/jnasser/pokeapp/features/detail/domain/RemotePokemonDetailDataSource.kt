package com.jnasser.pokeapp.features.detail.domain

import com.jnasser.pokeapp.core.domain.models.pokemon_detail.PokemonDetail
import com.jnasser.pokeapp.core.domain.util.error_handler.DataError
import com.jnasser.pokeapp.core.domain.util.error_handler.Result


interface RemotePokemonDetailDataSource {
    suspend fun getRemotePokemonDetail(name: String): Result<PokemonDetail, DataError.Network>
}