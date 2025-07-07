package io.github.droidkaigi.confsched

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Extends
import io.github.droidkaigi.confsched.data.JvmDataGraph

@DependencyGraph(AppScope::class, isExtendable = true)
interface JvmAppGraph : AppGraph {
    @DependencyGraph.Factory
    fun interface Factory {
        fun createJvmAppGraph(
            @Extends jvmDataGraph: JvmDataGraph,
        ): JvmAppGraph
    }
}
