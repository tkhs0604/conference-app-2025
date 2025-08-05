package io.github.droidkaigi.confsched

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import io.github.droidkaigi.confsched.component.KaigiNavigationScaffold
import io.github.droidkaigi.confsched.component.MainScreenTab
import io.github.droidkaigi.confsched.navigation.component.NavHostWithSharedAxisX
import io.github.droidkaigi.confsched.navigation.extension.navigateToAboutTab
import io.github.droidkaigi.confsched.navigation.extension.navigateToEventMapTab
import io.github.droidkaigi.confsched.navigation.extension.navigateToFavoritesTab
import io.github.droidkaigi.confsched.navigation.extension.navigateToProfileCardTab
import io.github.droidkaigi.confsched.navigation.extension.navigateToSearch
import io.github.droidkaigi.confsched.navigation.extension.navigateToTimetableItemDetail
import io.github.droidkaigi.confsched.navigation.extension.navigateToTimetableTab
import io.github.droidkaigi.confsched.navigation.graph.aboutTabNavGraph
import io.github.droidkaigi.confsched.navigation.graph.eventMapTabNavGraph
import io.github.droidkaigi.confsched.navigation.graph.favoritesTabNavGraph
import io.github.droidkaigi.confsched.navigation.graph.profileCardTabNavGraph
import io.github.droidkaigi.confsched.navigation.graph.timetableTabNavGraph
import io.github.droidkaigi.confsched.navigation.route.AboutTabRoute
import io.github.droidkaigi.confsched.navigation.route.EventMapTabRoute
import io.github.droidkaigi.confsched.navigation.route.FavoritesTabRoute
import io.github.droidkaigi.confsched.navigation.route.ProfileCardTabRoute
import io.github.droidkaigi.confsched.navigation.route.TimetableTabRoute

context(appGraph: AppGraph)
@Composable
actual fun KaigiAppUi() {
    val navController = rememberNavController()
    val externalNavController = rememberExternalNavController()
    val hazeState = rememberHazeState()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentTab by remember {
        derivedStateOf {
            navBackStackEntry?.destination?.let { destination ->
                val mainTabRoutes = listOf(TimetableTabRoute, EventMapTabRoute, FavoritesTabRoute, AboutTabRoute, ProfileCardTabRoute)
                val matchedMainTabRoute = mainTabRoutes.firstOrNull { mainTabRoute -> destination.hasRoute(mainTabRoute.rootRouteClass) } ?: return@derivedStateOf null
                when (matchedMainTabRoute) {
                    TimetableTabRoute -> MainScreenTab.Timetable
                    EventMapTabRoute -> MainScreenTab.EventMap
                    FavoritesTabRoute -> MainScreenTab.Favorite
                    AboutTabRoute -> MainScreenTab.About
                    ProfileCardTabRoute -> MainScreenTab.ProfileCard
                }
            }
        }
    }

    KaigiNavigationScaffold(
        currentTab = currentTab,
        hazeState = hazeState,
        onTabSelected = { tab ->
            when (tab) {
                MainScreenTab.Timetable -> navController.navigateToTimetableTab()
                MainScreenTab.EventMap -> navController.navigateToEventMapTab()
                MainScreenTab.Favorite -> navController.navigateToFavoritesTab()
                MainScreenTab.About -> navController.navigateToAboutTab()
                MainScreenTab.ProfileCard -> navController.navigateToProfileCardTab()
            }
        },
    ) {
        NavHostWithSharedAxisX(
            navController = navController,
            startDestination = TimetableTabRoute,
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState),
        ) {
            timetableTabNavGraph(
                onSearchClick = navController::navigateToSearch,
                onTimetableItemClick = navController::navigateToTimetableItemDetail,
                onBackClick = { navController.popBackStack() },
                onLinkClick = externalNavController::navigate,
                onShareClick = externalNavController::onShareClick,
                onAddCalendarClick = externalNavController::navigateToCalendarRegistration,
            )
            eventMapTabNavGraph()
            favoritesTabNavGraph(
                onBackClick = { navController.popBackStack() },
                onLinkClick = externalNavController::navigate,
                onShareClick = externalNavController::onShareClick,
                onAddCalendarClick = externalNavController::navigateToCalendarRegistration,
            )
            aboutTabNavGraph(
                onAboutItemClick = {
                    // TODO
                }
            )
            profileCardTabNavGraph()
        }
    }
}
