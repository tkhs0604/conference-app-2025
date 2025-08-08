package io.github.droidkaigi.confsched.navigation.extension

import androidx.navigation.NavController
import io.github.droidkaigi.confsched.navigation.route.ProfileCardTabRoute

fun NavController.navigateToProfileCardTab() {
    navigate(ProfileCardTabRoute) {
        launchSingleTop = true
        popUpTo<ProfileCardTabRoute>()
    }
}
