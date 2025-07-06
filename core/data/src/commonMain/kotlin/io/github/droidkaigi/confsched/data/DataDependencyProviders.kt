package io.github.droidkaigi.confsched.data

import de.jensklingenberg.ktorfit.Ktorfit
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.ContributesTo
import dev.zacsweers.metro.Named
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.data.core.defaultJson
import io.github.droidkaigi.confsched.data.sessions.FakeSessionsApiClient
import io.github.droidkaigi.confsched.data.sessions.SessionsApiClient
import io.github.droidkaigi.confsched.data.sessions.TimetableItemQueryKeyImpl
import io.github.droidkaigi.confsched.data.sessions.TimetableSubscriptionKeyImpl
import io.github.droidkaigi.confsched.data.user.FavoriteTimetableIdsSubscriptionKeyImpl
import io.github.droidkaigi.confsched.data.user.FavoriteTimetableItemIdMutationKeyImpl
import io.github.droidkaigi.confsched.data.user.UserDataStoreImpl
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableIdsSubscriptionKey
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableItemIdMutationKey
import io.github.droidkaigi.confsched.model.data.TimetableItemQueryKey
import io.github.droidkaigi.confsched.model.data.TimetableSubscriptionKey
import io.github.droidkaigi.confsched.model.data.UserDataStore
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

@ContributesTo(AppScope::class)
public interface DataDependencyProviders {
    @Named("apiBaseUrl")
    public val apiBaseUrl: String

    @Binds
    public val FavoriteTimetableIdsSubscriptionKeyImpl.bind: FavoriteTimetableIdsSubscriptionKey

    @Binds
    public val TimetableSubscriptionKeyImpl.bind: TimetableSubscriptionKey

    @Binds
    public val FakeSessionsApiClient.bind: SessionsApiClient

    @Binds
    public val UserDataStoreImpl.bind: UserDataStore

    @Binds
    public val FavoriteTimetableItemIdMutationKeyImpl.bind: FavoriteTimetableItemIdMutationKey

    @Binds
    public fun bindTimetableItemQueryKeyFactory(impl: TimetableItemQueryKeyImpl.Factory): TimetableItemQueryKey.Factory

    @Provides
    public fun provideTimetableItemQueryKeyImplFactory(
        sessionsApiClient: SessionsApiClient,
    ): TimetableItemQueryKeyImpl.Factory {
        return TimetableItemQueryKeyImpl.Factory(sessionsApiClient)
    }


    @Named("apiBaseUrl")
    @Provides
    public fun provideApiBaseUrl(): String {
        return "TODO: Provide API base URL in a proper way"
    }

    @Provides
    public fun provideKtorfit(
        httpClient: HttpClient,
        @Named("apiBaseUrl") apiBaseUrl: String,
    ): Ktorfit {
        return Ktorfit.Builder()
            .httpClient(httpClient)
            .baseUrl(apiBaseUrl)
            .build()
    }

    @Provides
    public fun provideJson(): Json {
        return defaultJson()
    }
}
