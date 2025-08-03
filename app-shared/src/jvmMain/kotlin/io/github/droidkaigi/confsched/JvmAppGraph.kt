package io.github.droidkaigi.confsched

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import io.github.droidkaigi.confsched.data.DataScope

@DependencyGraph(
    scope = AppScope::class,
    additionalScopes = [DataScope::class],
    isExtendable = true,
)
interface JvmAppGraph : AppGraph
