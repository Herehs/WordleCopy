package com.herehs.worldecopy.data.local.token_storage

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.herehs.worldecopy.data.local.token_storage.dto.WebToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenStorageImpl(
    val dataStore: DataStore<Preferences>
) : TokenStorage {

    private val refresh = stringPreferencesKey("refresh")

    private val access = stringPreferencesKey("access")

    override suspend fun save(token: WebToken) {
        dataStore.updateData {
            it.toMutablePreferences().also { preferences ->
                preferences[refresh] = token.refresh
                preferences[access] = token.access
            }
        }
    }

    override suspend fun get(): Flow<WebToken?> = dataStore.data.map { preferences ->
        val refreshToken = preferences[refresh]
        val accessToken = preferences[access]

        if(refreshToken == null || accessToken == null){
            null
        } else {
            WebToken(
                access = accessToken,
                refresh = refreshToken
            )
        }
    }

    override suspend fun clear(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}