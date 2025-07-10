package io.github.droidkaigi.confsched.favorites

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.common.scope.FavoritesScope
import io.github.droidkaigi.confsched.context.ScreenContext

@ContributesGraphExtension(FavoritesScope::class)
interface FavoritesScreenContext : ScreenContext {

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createFavoritesScreenContext(): FavoritesScreenContext
    }
}
