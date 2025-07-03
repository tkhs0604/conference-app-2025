package io.github.droidkaigi.confsched.testing

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import io.github.droidkaigi.confsched.sessions.TimetableScreenContext

@DependencyGraph(
    scope = AppScope::class,
    isExtendable = true,
)
interface TestAppGraph : TimetableScreenContext.Factory, PlatformTestDependencies
