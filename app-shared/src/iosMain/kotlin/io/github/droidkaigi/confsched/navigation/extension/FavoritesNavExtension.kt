package io.github.droidkaigi.confsched.navigation.extension

import androidx.navigation.NavController
import io.github.droidkaigi.confsched.navigation.route.FavoritesTabRoute

fun NavController.navigateToFavoritesTab() {
    navigate(FavoritesTabRoute) {
        launchSingleTop = true
        popUpTo<FavoritesTabRoute>()
    }
}
