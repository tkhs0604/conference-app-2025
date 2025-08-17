package io.github.droidkaigi.confsched.data.sessions

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.SessionCacheDataStoreQualifier
import io.github.droidkaigi.confsched.data.sessions.response.SessionsAllResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json

@Inject
public class SessionCacheDataStore(
    @param:SessionCacheDataStoreQualifier private val dataStore: DataStore<Preferences>,
    private val json: Json,
) {
    public suspend fun save(sessionsAllResponse: SessionsAllResponse) {
        dataStore.edit { preferences ->
            preferences[DATA_STORE_TIMETABLE_KEY] = json.encodeToString(sessionsAllResponse)
        }
    }

    public suspend fun getCache(): SessionsAllResponse? {
        return dataStore.data
            .map { preferences ->
                val serializedCache = preferences[DATA_STORE_TIMETABLE_KEY] ?: return@map null
                try {
                    json.decodeFromString(SessionsAllResponse.serializer(), serializedCache)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    null
                }
            }.firstOrNull()
    }

    public fun getCacheSync(): SessionsAllResponse? {
        return runBlocking { getCache() }
    }

    public fun getCacheStream(): Flow<SessionsAllResponse> {
        return dataStore.data.mapNotNull { preferences ->
            val serializedCache = preferences[DATA_STORE_TIMETABLE_KEY] ?: return@mapNotNull null
            json.decodeFromString(SessionsAllResponse.serializer(), serializedCache)
        }
    }

    private companion object {
        private val DATA_STORE_TIMETABLE_KEY = stringPreferencesKey("DATA_STORE_TIMETABLE_KEY")
    }
}
