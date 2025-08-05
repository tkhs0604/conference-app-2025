package io.github.droidkaigi.confsched

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3ExpressiveApi
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import io.github.droidkaigi.confsched.component.KaigiNavigationScaffold
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.component.NavDisplayWithSharedAxisX
import io.github.droidkaigi.confsched.component.MainScreenTab
import io.github.droidkaigi.confsched.model.about.AboutItem
import io.github.droidkaigi.confsched.naventry.aboutEntry
import io.github.droidkaigi.confsched.naventry.contributorsEntry
import io.github.droidkaigi.confsched.naventry.eventMapEntry
import io.github.droidkaigi.confsched.naventry.favoritesEntry
import io.github.droidkaigi.confsched.naventry.profileCardNavEntry
import io.github.droidkaigi.confsched.naventry.sessionEntries
import io.github.droidkaigi.confsched.navigation.rememberNavBackStack
import io.github.droidkaigi.confsched.navigation.sceneStrategy
import io.github.droidkaigi.confsched.navkey.AboutNavKey
import io.github.droidkaigi.confsched.navkey.EventMapNavKey
import io.github.droidkaigi.confsched.navkey.FavoritesNavKey
import io.github.droidkaigi.confsched.navkey.ProfileCardNavKey
import io.github.droidkaigi.confsched.navkey.SearchNavKey
import io.github.droidkaigi.confsched.navkey.TimetableItemDetailNavKey
import io.github.droidkaigi.confsched.navkey.TimetableNavKey
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
    val backStack = rememberNavBackStack(TimetableNavKey)
    val externalNavController = rememberExternalNavController()
    val hazeState = rememberHazeState()

    SwrClientProvider(SwrCachePlus(SwrCacheScope())) {
        KaigiTheme {
            Surface {
                KaigiNavigationScaffold(
                    currentTab = backStack.lastOrNull()?.let {
                        when (it) {
                            is TimetableNavKey -> MainScreenTab.Timetable
                            is EventMapNavKey -> MainScreenTab.EventMap
                            is FavoritesNavKey -> MainScreenTab.Favorite
                            is AboutNavKey -> MainScreenTab.About
                            is ProfileCardNavKey -> MainScreenTab.ProfileCard
                            else -> null
                        }
                    },
                    onTabSelected = {
                        val navKey = when (it) {
                            MainScreenTab.Timetable -> TimetableNavKey
                            MainScreenTab.EventMap -> EventMapNavKey
                            MainScreenTab.Favorite -> FavoritesNavKey
                            MainScreenTab.About -> AboutNavKey
                            MainScreenTab.ProfileCard -> ProfileCardNavKey
                        }
                        backStack.clear()
                        backStack.add(navKey)
                    },
                    hazeState = hazeState,
                ) {
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
                                onTimetableItemClick = {
                                    if (backStack.lastOrNull() is TimetableItemDetailNavKey) {
                                        backStack.removeLastOrNull()
                                    }
                                    backStack.add(TimetableItemDetailNavKey(it))
                                }
                            )
                            contributorsEntry()
                            favoritesEntry(
                                onTimetableItemClick = {
                                    if (backStack.lastOrNull() is TimetableItemDetailNavKey) {
                                        backStack.removeLastOrNull()
                                    }
                                    backStack.add(TimetableItemDetailNavKey(it))
                                }
                            )
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
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .hazeSource(hazeState)
                    )
                }
            }
        }
    }
}
