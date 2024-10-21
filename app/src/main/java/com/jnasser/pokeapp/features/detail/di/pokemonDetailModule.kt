package com.jnasser.pokeapp.features.detail.di

import com.jnasser.pokeapp.features.detail.presentation.PokemonDetailViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val pokemonDetailModule = module {
    viewModelOf(::PokemonDetailViewModel)
}