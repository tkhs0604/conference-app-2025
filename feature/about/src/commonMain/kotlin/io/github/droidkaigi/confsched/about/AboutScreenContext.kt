package io.github.droidkaigi.confsched.about

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.common.scope.AboutScope
import io.github.droidkaigi.confsched.context.ScreenContext

@ContributesGraphExtension(AboutScope::class)
interface AboutScreenContext : ScreenContext {

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createAboutScreenContext(): AboutScreenContext
    }
}
