package io.github.droidkaigi.confsched.contributors

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.common.scope.ContributorsScope

@ContributesGraphExtension(ContributorsScope::class)
interface ContributorsScreenContext {
    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createContributorsScreenContext(): ContributorsScreenContext
    }
}
