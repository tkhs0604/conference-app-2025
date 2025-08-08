package io.github.droidkaigi.confsched.naventry

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import io.github.droidkaigi.confsched.AppGraph
import io.github.droidkaigi.confsched.about.AboutScreenRoot
import io.github.droidkaigi.confsched.about.rememberAboutScreenContextRetained
import io.github.droidkaigi.confsched.model.about.AboutItem
import io.github.droidkaigi.confsched.navigation.listDetailSceneStrategyDetailPaneMetaData
import io.github.droidkaigi.confsched.navigation.listDetailSceneStrategyListPaneMetaData
import io.github.droidkaigi.confsched.navkey.AboutNavKey

context(appGraph: AppGraph)
fun EntryProviderBuilder<NavKey>.aboutEntry(
    onAboutItemClick: (AboutItem) -> Unit
) {
    entry<AboutNavKey>(
        metadata = listDetailSceneStrategyListPaneMetaData(),
    ) {
        with(appGraph.rememberAboutScreenContextRetained()) {
            AboutScreenRoot(
                onAboutItemClick = onAboutItemClick,
            )
        }
    }
}
