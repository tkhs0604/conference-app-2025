package io.github.droidkaigi.confsched.naventry

import androidx.navigation3.runtime.EntryProviderBuilder
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import io.github.droidkaigi.confsched.AppGraph
import io.github.droidkaigi.confsched.ExternalNavController
import io.github.droidkaigi.confsched.droidkaigiui.navigation.rememberNavBackStack
import io.github.droidkaigi.confsched.main.MainScreenRoot
import io.github.droidkaigi.confsched.main.MainScreenTab
import io.github.droidkaigi.confsched.model.about.AboutItem
import io.github.droidkaigi.confsched.navkey.AboutNavKey
import io.github.droidkaigi.confsched.navkey.EventMapNavKey
import io.github.droidkaigi.confsched.navkey.FavoritesNavKey
import io.github.droidkaigi.confsched.navkey.MainNavKey
import io.github.droidkaigi.confsched.navkey.ProfileCardNavKey
import io.github.droidkaigi.confsched.navkey.SearchNavKey
import io.github.droidkaigi.confsched.navkey.TimetableItemDetailNavKey
import io.github.droidkaigi.confsched.navkey.TimetableNavKey

context(appGraph: AppGraph)
fun EntryProviderBuilder<NavKey>.mainNestedEntry(externalNavController: ExternalNavController) {
    entry<MainNavKey> {
        val mainNestedBackStack = rememberNavBackStack(TimetableNavKey)
        MainScreenRoot(
            currentTab = mainNestedBackStack.lastOrNull()?.let {
                when (it) {
                    is TimetableNavKey -> MainScreenTab.Timetable
                    is EventMapNavKey -> MainScreenTab.EventMap
                    is FavoritesNavKey -> MainScreenTab.Favorite
                    is AboutNavKey -> MainScreenTab.About
                    is ProfileCardNavKey -> MainScreenTab.ProfileCard
                    else -> null
                }
            },
            backStack = mainNestedBackStack,
            onTabSelected = {
                val navKey = when (it) {
                    MainScreenTab.Timetable -> TimetableNavKey
                    MainScreenTab.EventMap -> EventMapNavKey
                    MainScreenTab.Favorite -> FavoritesNavKey
                    MainScreenTab.About -> AboutNavKey
                    MainScreenTab.ProfileCard -> ProfileCardNavKey
                }
                mainNestedBackStack.clear()
                mainNestedBackStack.add(navKey)
            },
            onBack = { keysToRemove -> repeat(keysToRemove) { mainNestedBackStack.removeLastOrNull() } },
            entryProvider = entryProvider {
                sessionEntries(
                    onBackClick = { mainNestedBackStack.removeLastOrNull() },
                    onAddCalendarClick = externalNavController::navigateToCalendarRegistration,
                    onShareClick = externalNavController::onShareClick,
                    onLinkClick = externalNavController::navigate,
                    onSearchClick = { mainNestedBackStack.add(SearchNavKey) },
                    onTimetableItemClick = { mainNestedBackStack.add(TimetableItemDetailNavKey(it)) }
                )
                contributorsEntry()
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
                favoritesEntry()
                profileCardNavEntry()
            }
        )
    }
}
