package io.github.droidkaigi.confsched.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.droidkaigi.confsched.AppGraph
import io.github.droidkaigi.confsched.sessions.TimetableScreenRoot
import io.github.droidkaigi.confsched.sessions.rememberTimetableScreenContextRetained

context(appGraph: AppGraph)
fun NavGraphBuilder.timetableNavGraph() {
    navigation<TimetableTabRoute>(
        startDestination = TimetableRoute,
    ) {
        composable<TimetableRoute> {
            with(appGraph.rememberTimetableScreenContextRetained()) {
                TimetableScreenRoot(
                    onSearchClick = {},
                    onTimetableItemClick = {},
                )
            }
        }
    }
}
