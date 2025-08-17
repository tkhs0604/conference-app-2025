package io.github.droidkaigi.confsched.testing.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.createGraph
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.data.contributors.DefaultContributorsApiClient
import io.github.droidkaigi.confsched.data.sessions.DefaultSessionsApiClient
import kotlinx.coroutines.CoroutineDispatcher

@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class],
    isExtendable = true,
    excludes = [
        DefaultSessionsApiClient::class,
        DefaultContributorsApiClient::class,
        CoroutineDispatcher::class,
    ],
)
internal interface JvmTestAppGraph : TestAppGraph

internal actual fun createTestAppGraph(): TestAppGraph {
    return createGraph<JvmTestAppGraph>()
}
