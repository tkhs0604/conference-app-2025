package io.github.droidkaigi.confsched

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.buildconfig.JvmBuildConfigProvider
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.model.buildconfig.BuildConfigProvider

@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class],
    isExtendable = true,
)
interface JvmAppGraph : AppGraph {

    @Provides
    fun provideBuildConfigProvider(): BuildConfigProvider {
        return JvmBuildConfigProvider()
    }
}
