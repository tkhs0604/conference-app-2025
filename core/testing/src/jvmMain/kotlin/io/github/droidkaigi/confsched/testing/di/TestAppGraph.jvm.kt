package io.github.droidkaigi.confsched.testing.di

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.createGraph
import io.github.droidkaigi.confsched.data.DataScope

@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class],
    isExtendable = true,
)
interface JvmTestAppGraph : TestAppGraph

actual fun createTestAppGraph(): TestAppGraph {
    return createGraph<JvmTestAppGraph>()
}
