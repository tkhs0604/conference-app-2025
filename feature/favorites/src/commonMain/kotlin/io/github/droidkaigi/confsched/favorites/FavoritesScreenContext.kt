package io.github.droidkaigi.confsched.favorites

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.common.scope.FavoritesScope
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableIdsSubscriptionKey
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableItemIdMutationKey
import io.github.droidkaigi.confsched.model.data.TimetableSubscriptionKey

@ContributesGraphExtension(FavoritesScope::class)
interface FavoritesScreenContext : ScreenContext {
    val timetableSubscriptionKey: TimetableSubscriptionKey
    val favoriteTimetableIdsSubscriptionKey: FavoriteTimetableIdsSubscriptionKey
    val favoriteTimetableItemIdMutationKey: FavoriteTimetableItemIdMutationKey

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createFavoritesScreenContext(): FavoritesScreenContext
    }
}
