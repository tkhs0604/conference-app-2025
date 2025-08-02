package io.github.droidkaigi.confsched.naventry

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import io.github.droidkaigi.confsched.AppGraph
import io.github.droidkaigi.confsched.contributors.ContributorsScreenRoot
import io.github.droidkaigi.confsched.contributors.rememberContributorsScreenContextRetained
import io.github.droidkaigi.confsched.navkey.ContributorsNavKey

context(appGraph: AppGraph)
fun EntryProviderBuilder<NavKey>.contributorsEntry() {
    entry<ContributorsNavKey> {
        with(appGraph.rememberContributorsScreenContextRetained()) {
            ContributorsScreenRoot()
        }
    }
}
