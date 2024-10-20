package com.jnasser.pokeapp.di

import com.jnasser.pokeapp.PokemonApp
import kotlinx.coroutines.CoroutineScope
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single<CoroutineScope> {
        (androidApplication() as PokemonApp).applicationScope
    }
}