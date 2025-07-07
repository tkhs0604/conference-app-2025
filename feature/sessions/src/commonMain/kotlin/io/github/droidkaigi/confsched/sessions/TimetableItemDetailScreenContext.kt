package io.github.droidkaigi.confsched.sessions

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.common.scope.TimetableDetailScope
import io.github.droidkaigi.confsched.context.ScreenContext

@ContributesGraphExtension(TimetableDetailScope::class)
interface TimetableItemDetailScreenContext : ScreenContext {
    val navKey: TimetableItemDetailNavKey

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createTimetableDetailScreenContext(
            @Provides navKey: TimetableItemDetailNavKey,
        ): TimetableItemDetailScreenContext
    }
}
