package com.jnasser.pokeapp.core.di

import com.jnasser.pokeapp.core.data.network.HttpClientFactory
import org.koin.dsl.module

val coreModule = module {
    single {
        HttpClientFactory().build()
    }
}