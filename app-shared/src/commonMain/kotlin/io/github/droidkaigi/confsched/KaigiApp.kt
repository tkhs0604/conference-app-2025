package io.github.droidkaigi.confsched

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.SceneStrategy
import io.github.droidkaigi.confsched.contributors.ContributorsScreenRoot
import io.github.droidkaigi.confsched.contributors.rememberContributorsScreenContextRetained
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.droidkaigiui.NavDisplayWithSharedAxisX
import io.github.droidkaigi.confsched.favorites.FavoritesScreenRoot
import io.github.droidkaigi.confsched.favorites.rememberFavoritesScreenContextRetained
import io.github.droidkaigi.confsched.navkey.ContributorsNavKey
import io.github.droidkaigi.confsched.navkey.FavoritesNavKey
import io.github.droidkaigi.confsched.navkey.SearchNavKey
import io.github.droidkaigi.confsched.navkey.TimetableItemDetailNavKey
import io.github.droidkaigi.confsched.navkey.TimetableNavKey
import io.github.droidkaigi.confsched.sessions.SearchScreenRoot
import io.github.droidkaigi.confsched.sessions.TimetableItemDetailScreenRoot
import io.github.droidkaigi.confsched.sessions.TimetableScreenRoot
import io.github.droidkaigi.confsched.sessions.rememberSearchScreenContextRetained
import io.github.droidkaigi.confsched.sessions.rememberTimetableItemDetailScreenContextRetained
import io.github.droidkaigi.confsched.sessions.rememberTimetableScreenContextRetained
import soil.query.SwrCachePlus
import soil.query.SwrCacheScope
import soil.query.annotation.ExperimentalSoilQueryApi
import soil.query.compose.SwrClientProvider

@Composable
expect fun rememberBackStack(elements: NavKey): MutableList<NavKey>

@Composable
expect fun sceneStrategy(): SceneStrategy<NavKey>

expect fun listDetailSceneStrategyListPaneMetaData(): Map<String, Any>

expect fun listDetailSceneStrategyDetailPaneMetaData(): Map<String, Any>

@Composable
expect fun rememberExternalNavController(): ExternalNavController

context(appGraph: AppGraph)
@OptIn(ExperimentalMaterial3ExpressiveApi::class, ExperimentalSoilQueryApi::class)
@Composable
fun KaigiApp() {
    val backStack = rememberBackStack(TimetableNavKey)
    val externalNavController = rememberExternalNavController()

    SwrClientProvider(SwrCachePlus(SwrCacheScope())) {
        KaigiTheme {
            Surface {
                NavDisplayWithSharedAxisX(
                    backStack = backStack,
                    onBack = { keysToRemove -> repeat(keysToRemove) { backStack.removeLastOrNull() } },
                    sceneStrategy = sceneStrategy(),
                    entryProvider = entryProvider {
                        entry<TimetableNavKey>(metadata = listDetailSceneStrategyListPaneMetaData()) {
                            with(appGraph.rememberTimetableScreenContextRetained()) {
                                TimetableScreenRoot(
                                    onSearchClick = { backStack.add(SearchNavKey) },
                                    onTimetableItemClick = { backStack.add(TimetableItemDetailNavKey(it)) }
                                )
                            }
                        }
                        entry<TimetableItemDetailNavKey>(metadata = listDetailSceneStrategyDetailPaneMetaData()) {
                            with(appGraph.rememberTimetableItemDetailScreenContextRetained(it.id)) {
                                TimetableItemDetailScreenRoot(
                                    onBackClick = { backStack.removeLastOrNull() },
                                    onAddCalendarClick = externalNavController::navigateToCalendarRegistration,
                                    onShareClick = externalNavController::onShareClick,
                                    onLinkClick = externalNavController::navigate,
                                )
                            }
                        }
                        entry<SearchNavKey> {
                            with(appGraph.rememberSearchScreenContextRetained()) {
                                SearchScreenRoot()
                            }
                        }
                        entry<ContributorsNavKey> {
                            with(appGraph.rememberContributorsScreenContextRetained()) {
                                ContributorsScreenRoot()
                            }
                        }
                        entry<FavoritesNavKey> {
                            with(appGraph.rememberFavoritesScreenContextRetained()) {
                                FavoritesScreenRoot()
                            }
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
