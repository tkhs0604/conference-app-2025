package io.github.droidkaigi.confsched.contributors

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.common.scope.ContributorsScope
import io.github.droidkaigi.confsched.model.contributors.ContributorsQueryKey

@ContributesGraphExtension(ContributorsScope::class)
interface ContributorsScreenContext {
    val contributorsQueryKey: ContributorsQueryKey

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createContributorsScreenContext(): ContributorsScreenContext
    }
}
