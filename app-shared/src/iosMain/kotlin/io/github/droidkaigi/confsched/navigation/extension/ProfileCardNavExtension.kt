package io.github.droidkaigi.confsched.navigation.extension

import androidx.navigation.NavController
import io.github.droidkaigi.confsched.navigation.route.ProfileTabRoute

fun NavController.navigateToProfileCardTab() {
    navigate(ProfileTabRoute) {
        launchSingleTop = true
        popUpTo<ProfileTabRoute>()
    }
}
