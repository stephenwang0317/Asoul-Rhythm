package com.example.bellavoice.model.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class ThemeInfoManager(private val context: Context) {
    companion object {
        private val Context.themeStore: DataStore<Preferences> by preferencesDataStore("theme_store")

        val THEME_ID = intPreferencesKey("id")
    }

    val id: Flow<Int> = context.themeStore.data.map { it[THEME_ID] ?: 1 }

    suspend fun save(id: Int){
        context.themeStore.edit {
            it[THEME_ID] = id
        }
    }

}