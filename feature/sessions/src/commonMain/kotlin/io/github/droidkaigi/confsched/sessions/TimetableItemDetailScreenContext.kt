package io.github.droidkaigi.confsched.sessions

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.model.data.TimetableItemQueryKey

abstract class TimetableDetailScope private constructor()

@ContributesGraphExtension(TimetableDetailScope::class)
interface TimetableItemDetailScreenContext : ScreenContext {
    val navKey: TimetableItemDetailNavKey

    val timetableItemQueryKeyFactory: TimetableItemQueryKey.Factory

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createTimetableDetailScreenContext(
            @Provides navKey: TimetableItemDetailNavKey,
        ): TimetableItemDetailScreenContext
    }
}
