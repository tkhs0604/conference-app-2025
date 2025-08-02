package io.github.droidkaigi.confsched

import android.content.Context
import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.buildconfig.AndroidBuildConfigProvider
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.model.buildconfig.BuildConfigProvider

@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class],
    isExtendable = true,
)
interface AndroidAppGraph : AppGraph {
    @DependencyGraph.Factory
    fun interface Factory {
        fun createAndroidAppGraph(@Provides applicationContext: Context): AndroidAppGraph
    }

    @Provides
    fun provideBuildConfigProvider(): BuildConfigProvider {
        return AndroidBuildConfigProvider()
    }
}
