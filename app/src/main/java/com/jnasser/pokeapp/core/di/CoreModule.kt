package com.jnasser.pokeapp.core.di

import android.content.SharedPreferences
import com.jnasser.pokeapp.core.data.PreferenceManager
import com.jnasser.pokeapp.core.data.network.HttpClientFactory
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val coreModule = module {
    single {
        HttpClientFactory().build()
    }

    single {
        PreferenceManager(androidApplication())
    }
}