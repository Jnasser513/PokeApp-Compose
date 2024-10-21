package com.jnasser.pokeapp.features.detail.di

import com.jnasser.pokeapp.features.detail.data.models.KtorRemotePokemonDetailDataSource
import com.jnasser.pokeapp.features.detail.data.models.PokemonDetailRepositoryImpl
import com.jnasser.pokeapp.features.detail.domain.PokemonDetailRepository
import com.jnasser.pokeapp.features.detail.domain.RemotePokemonDetailDataSource
import com.jnasser.pokeapp.features.detail.presentation.PokemonDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val pokemonDetailModule = module {
    singleOf(::KtorRemotePokemonDetailDataSource).bind<RemotePokemonDetailDataSource>()
    singleOf(::PokemonDetailRepositoryImpl).bind<PokemonDetailRepository>()

    viewModelOf(::PokemonDetailViewModel)
}