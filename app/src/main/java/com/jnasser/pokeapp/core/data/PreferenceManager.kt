package com.jnasser.pokeapp.core.data

import android.content.Context
import android.content.SharedPreferences
import io.ktor.utils.io.concurrent.shared

class PreferenceManager(context: Context) {
    private val sharedPreference =
        context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    fun setData(key: String, value: String) =
        sharedPreference.edit().putString(key, value).apply()

    fun getData(key: String) = sharedPreference.getString(key, "")

    companion object {
        const val SHARED_PREFERENCES_NAME = "poke_app_db"
    }
}