package io.github.droidkaigi.confsched

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph

@DependencyGraph(AppScope::class, isExtendable = true)
interface JvmAppGraph : AppGraph
