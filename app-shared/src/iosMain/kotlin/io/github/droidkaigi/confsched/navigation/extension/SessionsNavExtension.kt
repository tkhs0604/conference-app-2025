package io.github.droidkaigi.confsched.navigation.extension

import androidx.navigation.NavController
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import io.github.droidkaigi.confsched.navigation.route.SearchRoute
import io.github.droidkaigi.confsched.navigation.route.TimetableItemDetailRoute
import io.github.droidkaigi.confsched.navigation.route.TimetableTabRoute

fun NavController.navigateToTimetableTab() {
    navigate(TimetableTabRoute) {
        launchSingleTop = true
        popUpTo<TimetableTabRoute>()
    }
}

fun NavController.navigateToTimetableItemDetail(
    timetableItemId: TimetableItemId,
) {
    navigate(TimetableItemDetailRoute(timetableItemId.value))
}

fun NavController.navigateToSearch() {
    navigate(SearchRoute)
}
