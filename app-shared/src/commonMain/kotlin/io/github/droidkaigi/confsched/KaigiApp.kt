package io.github.droidkaigi.confsched

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.droidkaigiui.navigation.NavDisplayWithSharedAxisX
import io.github.droidkaigi.confsched.droidkaigiui.navigation.rememberNavBackStack
import io.github.droidkaigi.confsched.droidkaigiui.navigation.sceneStrategy
import io.github.droidkaigi.confsched.model.about.AboutItem
import io.github.droidkaigi.confsched.naventry.aboutEntry
import io.github.droidkaigi.confsched.naventry.contributorsEntry
import io.github.droidkaigi.confsched.naventry.eventMapEntry
import io.github.droidkaigi.confsched.naventry.favoritesEntry
import io.github.droidkaigi.confsched.naventry.mainNestedEntry
import io.github.droidkaigi.confsched.naventry.profileCardNavEntry
import io.github.droidkaigi.confsched.naventry.sessionEntries
import io.github.droidkaigi.confsched.navkey.MainNavKey
import io.github.droidkaigi.confsched.navkey.SearchNavKey
import io.github.droidkaigi.confsched.navkey.TimetableItemDetailNavKey
import soil.query.SwrCachePlus
import soil.query.SwrCacheScope
import soil.query.annotation.ExperimentalSoilQueryApi
import soil.query.compose.SwrClientProvider

@Composable
expect fun rememberExternalNavController(): ExternalNavController

context(appGraph: AppGraph)
@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalSoilQueryApi::class)
@Composable
fun KaigiApp() {
    val backStack = rememberNavBackStack(MainNavKey)
    val externalNavController = rememberExternalNavController()

    SwrClientProvider(SwrCachePlus(SwrCacheScope())) {
        KaigiTheme {
            Surface {
                NavDisplayWithSharedAxisX(
                    backStack = backStack,
                    onBack = { keysToRemove -> repeat(keysToRemove) { backStack.removeLastOrNull() } },
                    sceneStrategy = sceneStrategy(),
                    entryProvider = entryProvider {
                        sessionEntries(
                            onBackClick = { backStack.removeLastOrNull() },
                            onAddCalendarClick = externalNavController::navigateToCalendarRegistration,
                            onShareClick = externalNavController::onShareClick,
                            onLinkClick = externalNavController::navigate,
                            onSearchClick = { backStack.add(SearchNavKey) },
                            onTimetableItemClick = { backStack.add(TimetableItemDetailNavKey(it)) }
                        )
                        contributorsEntry()
                        favoritesEntry()
                        eventMapEntry()
                        aboutEntry(
                            onAboutItemClick = { item ->
                                when (item) {
                                    AboutItem.Map -> TODO()
                                    AboutItem.Contributors -> TODO()
                                    AboutItem.Staff -> TODO()
                                    AboutItem.Sponsors -> TODO()
                                    AboutItem.CodeOfConduct -> TODO()
                                    AboutItem.License -> TODO()
                                    AboutItem.PrivacyPolicy -> TODO()
                                    AboutItem.Settings -> TODO()
                                    AboutItem.Youtube -> TODO()
                                    AboutItem.X -> TODO()
                                    AboutItem.Medium -> TODO()
                                }
                            }
                        )
                        profileCardNavEntry()
                        mainNestedEntry(externalNavController = externalNavController)
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
