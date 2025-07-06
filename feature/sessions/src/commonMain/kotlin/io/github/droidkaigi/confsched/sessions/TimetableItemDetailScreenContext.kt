package io.github.droidkaigi.confsched.sessions

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableIdsSubscriptionKey
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableItemIdMutationKey
import io.github.droidkaigi.confsched.model.data.TimetableItemQueryKey

abstract class TimetableDetailScope private constructor()

@ContributesGraphExtension(TimetableDetailScope::class)
interface TimetableItemDetailScreenContext : ScreenContext {
    val timetableItemQueryKeyFactory: TimetableItemQueryKey.Factory

    val favoriteTimetableIdsSubscriptionKey: FavoriteTimetableIdsSubscriptionKey

    val favoriteTimetableItemIdMutationKey: FavoriteTimetableItemIdMutationKey

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createTimetableDetailScreenContext(): TimetableItemDetailScreenContext
    }
}
