package io.github.droidkaigi.confsched.sessions

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.common.scope.TimetableDetailScope
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableIdsSubscriptionKey
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableItemIdMutationKey
import io.github.droidkaigi.confsched.model.data.TimetableItemQueryKey
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId

@ContributesGraphExtension(TimetableDetailScope::class, isExtendable = true)
interface TimetableItemDetailScreenContext : ScreenContext {
    val timetableItemQueryKey: TimetableItemQueryKey

    val favoriteTimetableIdsSubscriptionKey: FavoriteTimetableIdsSubscriptionKey

    val favoriteTimetableItemIdMutationKey: FavoriteTimetableItemIdMutationKey

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createTimetableDetailScreenContext(
            @Provides timetableItemId: TimetableItemId
        ): TimetableItemDetailScreenContext
    }
}
