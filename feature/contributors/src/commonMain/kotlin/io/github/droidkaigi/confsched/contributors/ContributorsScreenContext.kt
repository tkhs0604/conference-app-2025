package io.github.droidkaigi.confsched.contributors

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension

abstract class ContributorsScope private constructor()

@ContributesGraphExtension(ContributorsScope::class)
interface ContributorsScreenContext {
    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createContributorsScreenContext(): ContributorsScreenContext
    }
}
