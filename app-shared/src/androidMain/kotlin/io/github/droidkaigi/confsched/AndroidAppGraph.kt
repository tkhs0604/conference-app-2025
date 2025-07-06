package io.github.droidkaigi.confsched

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import dev.zacsweers.metro.Extends
import dev.zacsweers.metro.Includes
import io.github.droidkaigi.confsched.data.AndroidDataGraph

@DependencyGraph(AppScope::class, isExtendable = true)
interface AndroidAppGraph : AppGraph {
    @DependencyGraph.Factory
    fun interface Factory {
        fun createAndroidAppGraph(@Extends androidDataGraph: AndroidDataGraph): AndroidAppGraph
    }
}
