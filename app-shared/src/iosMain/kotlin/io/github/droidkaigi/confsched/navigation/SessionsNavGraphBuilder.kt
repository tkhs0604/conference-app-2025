package io.github.droidkaigi.confsched.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import androidx.navigation.toRoute
import io.github.droidkaigi.confsched.AppGraph
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import io.github.droidkaigi.confsched.sessions.SearchScreenRoot
import io.github.droidkaigi.confsched.sessions.TimetableItemDetailScreenRoot
import io.github.droidkaigi.confsched.sessions.TimetableScreenRoot
import io.github.droidkaigi.confsched.sessions.rememberSearchScreenContextRetained
import io.github.droidkaigi.confsched.sessions.rememberTimetableItemDetailScreenContextRetained
import io.github.droidkaigi.confsched.sessions.rememberTimetableScreenContextRetained

context(appGraph: AppGraph)
fun NavGraphBuilder.timetableTabNavGraph(
    onSearchClick: () -> Unit,
    onTimetableItemClick: (TimetableItemId) -> Unit,
    onBackClick: () -> Unit,
    onLinkClick: (String) -> Unit,
    onShareClick: (TimetableItem) -> Unit,
    onAddCalendarClick: (TimetableItem) -> Unit,
) {
    navigation<TimetableTabRoute>(
        startDestination = TimetableTabRoute.TimetableRoute,
    ) {
        timetableNavGraph(
            onSearchClick = onSearchClick,
            onTimetableItemClick = onTimetableItemClick,
        )
        timetableItemDetailNavGraph(
            onBackClick = onBackClick,
            onLinkClick = onLinkClick,
            onShareClick = onShareClick,
            onAddCalendarClick = onAddCalendarClick,
        )
        searchNavGraph()
    }
}

context(appGraph: AppGraph)
fun NavGraphBuilder.timetableNavGraph(
    onSearchClick: () -> Unit,
    onTimetableItemClick: (TimetableItemId) -> Unit,
) {
    composable<TimetableTabRoute.TimetableRoute> {
        with(appGraph.rememberTimetableScreenContextRetained()) {
            TimetableScreenRoot(
                onSearchClick = onSearchClick,
                onTimetableItemClick = onTimetableItemClick,
            )
        }
    }
}

context(appGraph: AppGraph)
fun NavGraphBuilder.timetableItemDetailNavGraph(
    onBackClick: () -> Unit,
    onLinkClick: (String) -> Unit,
    onShareClick: (TimetableItem) -> Unit,
    onAddCalendarClick: (TimetableItem) -> Unit,
) {
    composable<TimetableTabRoute.TimetableItemDetailRoute> {
        val timetableItemId =
            TimetableItemId(it.toRoute<TimetableTabRoute.TimetableItemDetailRoute>().id)
        with(appGraph.rememberTimetableItemDetailScreenContextRetained(timetableItemId)) {
            TimetableItemDetailScreenRoot(
                onBackClick = onBackClick,
                onLinkClick = onLinkClick,
                onShareClick = onShareClick,
                onAddCalendarClick = onAddCalendarClick,
            )
        }
    }
}

context(appGraph: AppGraph)
fun NavGraphBuilder.searchNavGraph() {
    composable<TimetableTabRoute.SearchRoute> {
        with(appGraph.rememberSearchScreenContextRetained()) {
            SearchScreenRoot()
        }
    }
}
