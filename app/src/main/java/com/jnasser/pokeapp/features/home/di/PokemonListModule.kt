package com.jnasser.pokeapp.features.home.di

import com.jnasser.pokeapp.features.home.data.KtorRemotePokemonListDataSource
import com.jnasser.pokeapp.features.home.data.PokemonListRepositoryImpl
import com.jnasser.pokeapp.features.home.domain.PokemonListRepository
import com.jnasser.pokeapp.features.home.domain.RemotePokemonListDataSource
import com.jnasser.pokeapp.features.home.presentation.PokemonListViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val pokemonListModule = module {
    singleOf(::KtorRemotePokemonListDataSource).bind<RemotePokemonListDataSource>()
    singleOf(::PokemonListRepositoryImpl).bind<PokemonListRepository>()

    viewModelOf(::PokemonListViewModel)
}