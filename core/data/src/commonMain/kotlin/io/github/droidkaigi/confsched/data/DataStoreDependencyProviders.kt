package io.github.droidkaigi.confsched.data

import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.Qualifier
import dev.zacsweers.metro.SingleIn
import io.github.droidkaigi.confsched.data.annotations.IoDispatcher
import io.github.droidkaigi.confsched.data.core.DataStorePathProducer
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import okio.Path.Companion.toPath

@Qualifier
public annotation class UserDataStoreQualifier

@Qualifier
public annotation class SessionCacheDataStoreQualifier

@ContributesTo(DataScope::class)
public interface DataStoreDependencyProviders {
    @SingleIn(DataScope::class)
    @UserDataStoreQualifier
    @Provides
    public fun provideDataStorePreferences(
        dataStorePathProducer: DataStorePathProducer,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.createWithPath(
            corruptionHandler = ReplaceFileCorruptionHandler({ emptyPreferences() }),
            migrations = emptyList(),
            scope = CoroutineScope(ioDispatcher),
            produceFile = { dataStorePathProducer.producePath(DATA_STORE_PREFERENCE_FILE_NAME).toPath() },
        )
    }

    @SingleIn(DataScope::class)
    @SessionCacheDataStoreQualifier
    @Provides
    public fun provideSessionCacheDataStore(
        dataStorePathProducer: DataStorePathProducer,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.createWithPath(
            corruptionHandler = ReplaceFileCorruptionHandler({ emptyPreferences() }),
            migrations = emptyList(),
            scope = CoroutineScope(ioDispatcher),
            produceFile = { dataStorePathProducer.producePath(DATA_STORE_CACHE_PREFERENCE_FILE_NAME).toPath() },
        )
    }

    private companion object Companion {
        private const val DATA_STORE_PREFERENCE_FILE_NAME = "confsched2025.preferences_pb"
        private const val DATA_STORE_CACHE_PREFERENCE_FILE_NAME = "confsched2025.cache.preferences_pb"
    }
}
