package org.d3if3169.doghotel.util

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.d3if3169.doghotel.model.User

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = "settings_preference"
)

class SettingsDataStore(private val context: Context) {
    companion object {
        private val IS_LIST = booleanPreferencesKey("isList")
        private val USER_NAME = stringPreferencesKey("name")
        private val USER_EMAIL = stringPreferencesKey("email")
        private val USER_PHOTO = stringPreferencesKey("photoUrl")
    }

    val layoutFlow: Flow<Boolean> =
        context.dataStore.data.map { preferences -> preferences[IS_LIST] ?: true }

    val userFlow: Flow<User> = context.dataStore.data.map {
        User(
            name = it[USER_NAME] ?: "",
            email = it[USER_EMAIL] ?: "",
            photoUrl = it[USER_PHOTO] ?: ""
        )
    }

    suspend fun saveLayout(isList: Boolean) {
        context.dataStore.edit { preferences -> preferences[IS_LIST] = isList}
    }

    suspend fun saveData(user: User) {
        context.dataStore.edit {
            it[USER_NAME] = user.name
            it[USER_EMAIL] = user.email
            it[USER_PHOTO] = user.photoUrl
        }
    }
}