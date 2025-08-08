package io.github.droidkaigi.confsched.navigation.extension

import androidx.navigation.NavController
import io.github.droidkaigi.confsched.navigation.route.AboutTabRoute

fun NavController.navigateToAboutTab() {
    navigate(AboutTabRoute) {
        launchSingleTop = true
        popUpTo<AboutTabRoute>()
    }
}
