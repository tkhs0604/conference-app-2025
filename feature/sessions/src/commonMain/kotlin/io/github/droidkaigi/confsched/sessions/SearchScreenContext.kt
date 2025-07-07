package io.github.droidkaigi.confsched.sessions

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.common.scope.SearchScope
import io.github.droidkaigi.confsched.context.ScreenContext

@ContributesGraphExtension(SearchScope::class)
interface SearchScreenContext : ScreenContext {
    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createSearchScreenContext(): SearchScreenContext
    }
}
