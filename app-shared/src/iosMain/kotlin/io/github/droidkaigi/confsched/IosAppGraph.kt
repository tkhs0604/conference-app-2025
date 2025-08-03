package io.github.droidkaigi.confsched

import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import de.jensklingenberg.ktorfit.Ktorfit
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.SingleIn
import dev.zacsweers.metro.createGraph
import io.github.droidkaigi.confsched.common.scope.TimetableDetailScope
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.data.DataStoreDependencyProviders
import io.github.droidkaigi.confsched.data.SessionCacheDataStoreQualifier
import io.github.droidkaigi.confsched.data.UserDataStoreQualifier
import io.github.droidkaigi.confsched.data.annotations.IoDispatcher
import io.github.droidkaigi.confsched.data.core.DataStorePathProducer
import io.github.droidkaigi.confsched.data.core.defaultJson
import io.github.droidkaigi.confsched.data.core.defaultKtorConfig
import io.github.droidkaigi.confsched.data.sessions.DefaultSessionsApiClient
import io.github.droidkaigi.confsched.data.sessions.DefaultTimetableItemQueryKey
import io.github.droidkaigi.confsched.data.sessions.DefaultTimetableSubscriptionKey
import io.github.droidkaigi.confsched.data.sessions.SessionsApiClient
import io.github.droidkaigi.confsched.data.user.DefaultFavoriteTimetableIdsSubscriptionKey
import io.github.droidkaigi.confsched.data.user.DefaultFavoriteTimetableItemIdMutationKey
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableIdsSubscriptionKey
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableItemIdMutationKey
import io.github.droidkaigi.confsched.model.data.TimetableItemQueryKey
import io.github.droidkaigi.confsched.model.data.TimetableSubscriptionKey
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import okio.Path.Companion.toPath
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSURL
import platform.Foundation.NSUserDomainMask

/**
 * The iOS dependency graph cannot currently be resolved by the compiler plugin.
 * Therefore, we need to define the iOS dependency graph manually.
 * For more details, see: https://github.com/ZacSweers/metro/issues/460
 */
@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class],
    isExtendable = true,
)
interface IosAppGraph : AppGraph {
    val sessionsApiClient: SessionsApiClient

    @Named("apiBaseUrl")
    @Provides
    fun provideApiBaseUrl(): String {
        return "https://ssot-api-staging.an.r.appspot.com/"
    }

    @Binds
    val DefaultTimetableSubscriptionKey.bind: TimetableSubscriptionKey

    @Binds
    val DefaultFavoriteTimetableIdsSubscriptionKey.bind: FavoriteTimetableIdsSubscriptionKey

    @Binds
    val DefaultSessionsApiClient.bind: SessionsApiClient

    @Binds
    val DefaultFavoriteTimetableItemIdMutationKey.bind: FavoriteTimetableItemIdMutationKey

    @Provides
    fun provideJson(): Json {
        return defaultJson()
    }

    @Provides
    fun provideHttpClient(json: Json): HttpClient {
        return HttpClient(Darwin) {
            defaultKtorConfig(json)
        }
    }

    @Provides
    fun provideKtorfit(
        httpClient: HttpClient,
        @Named("apiBaseUrl") apiBaseUrl: String,
    ): Ktorfit {
        return Ktorfit.Builder()
            .httpClient(httpClient)
            .baseUrl(apiBaseUrl)
            .build()
    }

    @SingleIn(DataScope::class)
    @UserDataStoreQualifier
    @Provides
    fun provideDataStorePreferences(
        dataStorePathProducer: DataStorePathProducer,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.createWithPath(
            corruptionHandler = ReplaceFileCorruptionHandler({ emptyPreferences() }),
            migrations = emptyList(),
            scope = CoroutineScope(ioDispatcher),
            produceFile = { dataStorePathProducer.producePath(DataStoreDependencyProviders.DATA_STORE_PREFERENCE_FILE_NAME).toPath() },
        )
    }

    @SingleIn(DataScope::class)
    @SessionCacheDataStoreQualifier
    @Provides
    fun provideSessionCacheDataStore(
        dataStorePathProducer: DataStorePathProducer,
        @IoDispatcher ioDispatcher: CoroutineDispatcher,
    ): DataStore<Preferences> {
        return PreferenceDataStoreFactory.createWithPath(
            corruptionHandler = ReplaceFileCorruptionHandler({ emptyPreferences() }),
            migrations = emptyList(),
            scope = CoroutineScope(ioDispatcher),
            produceFile = { dataStorePathProducer.producePath(DataStoreDependencyProviders.DATA_STORE_CACHE_PREFERENCE_FILE_NAME).toPath() },
        )
    }

    @OptIn(ExperimentalForeignApi::class)
    @Provides
    fun providesDataStorePathProducer(): DataStorePathProducer {
        return DataStorePathProducer { fileName ->
            val documentDirectory: NSURL? = NSFileManager.defaultManager.URLForDirectory(
                directory = NSDocumentDirectory,
                inDomain = NSUserDomainMask,
                appropriateForURL = null,
                create = false,
                error = null,
            )
            requireNotNull(documentDirectory).path + "/$fileName"
        }
    }

    @IoDispatcher
    @Provides
    fun provideIoDispatcher(): CoroutineDispatcher {
        // Since Kotlin/Native doesn't support Dispatchers.IO, we use Dispatchers.Default instead.
        return Dispatchers.Default
    }
}

@ContributesTo(TimetableDetailScope::class)
interface IosTimetableItemDetailGraph {
    @Binds
    val DefaultTimetableItemQueryKey.bind: TimetableItemQueryKey
}

fun createIosAppGraph(): IosAppGraph {
    return createGraph()
}
