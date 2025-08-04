package io.github.droidkaigi.confsched

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import dev.chrisbanes.haze.hazeSource
import dev.chrisbanes.haze.rememberHazeState
import io.github.droidkaigi.confsched.component.KaigiNavigationScaffold
import io.github.droidkaigi.confsched.component.MainScreenTab
import io.github.droidkaigi.confsched.navigation.TimetableTabRoute
import io.github.droidkaigi.confsched.navigation.timetableNavGraph

context(appGraph: AppGraph)
@Composable
actual fun KaigiAppUi() {
    val navController = rememberNavController()
    val hazeState = rememberHazeState()

    KaigiNavigationScaffold(
        currentTab = MainScreenTab.Timetable,
        hazeState = hazeState,
        onTabSelected = {},
    ) {
        NavHost(
            navController = navController,
            startDestination = TimetableTabRoute,
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(hazeState),
        ) {
            timetableNavGraph()
        }
    }
}
