package io.github.droidkaigi.confsched

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.DependencyGraph
import io.github.droidkaigi.confsched.sessions.SearchScreenContext
import io.github.droidkaigi.confsched.sessions.TimetableItemDetailScreenContext
import io.github.droidkaigi.confsched.sessions.TimetableScreenContext

@DependencyGraph(AppScope::class, isExtendable = true)
interface AppGraph :
    TimetableScreenContext.Factory,
    TimetableItemDetailScreenContext.Factory,
    SearchScreenContext.Factory,
    PlatformAppDependencyProvider
