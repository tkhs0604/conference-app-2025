package io.github.droidkaigi.confsched.sponsors

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.common.scope.SponsorsScope
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.model.sponsors.SponsorsQueryKey

@ContributesGraphExtension(scope= SponsorsScope::class)
interface SponsorsScreenContext: ScreenContext {
    val sponsorsQueryKey: SponsorsQueryKey

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createSponsorsScreenContext(): SponsorsScreenContext
    }
}
