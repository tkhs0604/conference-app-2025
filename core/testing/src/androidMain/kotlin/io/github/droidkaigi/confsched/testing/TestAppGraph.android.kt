package io.github.droidkaigi.confsched.testing

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import dev.zacsweers.metro.createGraph
import io.github.droidkaigi.confsched.data.DataScope

@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class],
    isExtendable = true,
)
interface AndroidTestAppGraph : TestAppGraph {
    @Provides
    fun provideContext(): Context {
        return ApplicationProvider.getApplicationContext<TestApplication>()
    }
}

actual fun createTestAppGraph(): TestAppGraph {
    return createGraph<AndroidTestAppGraph>()
}
