package com.suitmedia.mobile_test.core.data.local.preference

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UserPreferencesManager @Inject constructor(private val dataStore: DataStore<Preferences>) {

    companion object {
        private val KEY_USERNAME = stringPreferencesKey("username")
        private val KEY_SELECTED_USERNAME = stringPreferencesKey("selected_username") // Key for selected username
    }

    suspend fun saveUsername(username: String) {
        dataStore.edit { preferences ->
            preferences.remove(KEY_USERNAME)
            preferences[KEY_USERNAME] = username
        }
    }

    suspend fun saveSelectedUsername(selectedUsername: String) {
        dataStore.edit { preferences ->
            preferences.remove(KEY_SELECTED_USERNAME)
            preferences[KEY_SELECTED_USERNAME] = selectedUsername
        }
    }

    val username: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_USERNAME]
    }.distinctUntilChanged()

    val selectedUsername: Flow<String?> = dataStore.data.map { preferences ->
        preferences[KEY_SELECTED_USERNAME]
    }.distinctUntilChanged()

    suspend fun clearUser() {
        dataStore.edit { preferences ->
            preferences.remove(KEY_USERNAME)
            preferences.remove(KEY_SELECTED_USERNAME)
        }
    }
}
