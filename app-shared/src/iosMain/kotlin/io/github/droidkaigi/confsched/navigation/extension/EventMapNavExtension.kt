package io.github.droidkaigi.confsched.navigation.extension

import androidx.navigation.NavController
import io.github.droidkaigi.confsched.navigation.route.EventMapTabRoute

fun NavController.navigateToEventMapTab() {
    navigate(EventMapTabRoute) {
        launchSingleTop = true
        popUpTo<EventMapTabRoute>()
    }
}
