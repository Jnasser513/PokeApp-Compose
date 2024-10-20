package com.jnasser.pokeapp

import android.app.Application
import com.jnasser.pokeapp.core.di.coreModule
import com.jnasser.pokeapp.di.appModule
import com.jnasser.pokeapp.features.home.di.pokemonListModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class PokemonApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if(BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@PokemonApp)
            modules(
                appModule,
                pokemonListModule,
                coreModule
            )
        }
    }
}