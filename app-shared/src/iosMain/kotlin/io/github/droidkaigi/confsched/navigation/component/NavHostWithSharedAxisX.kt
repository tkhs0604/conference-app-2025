package io.github.droidkaigi.confsched.navigation.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.droidkaigi.confsched.navigation.materialSharedAxisXIn
import io.github.droidkaigi.confsched.navigation.materialSharedAxisXOut
import io.github.droidkaigi.confsched.navigation.rememberSlideDistance

@Composable
fun NavHostWithSharedAxisX(
    navController: NavHostController,
    startDestination: Any,
    modifier: Modifier = Modifier,
    builder: NavGraphBuilder.() -> Unit,
) {
    val slideDistance = rememberSlideDistance()

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = {
            materialSharedAxisXIn(
                forward = true,
                slideDistance = slideDistance,
            )
        },
        exitTransition = {
            materialSharedAxisXOut(
                forward = true,
                slideDistance = slideDistance,
            )
        },
        popEnterTransition = {
            materialSharedAxisXIn(
                forward = false,
                slideDistance = slideDistance,
            )
        },
        popExitTransition = {
            materialSharedAxisXOut(
                forward = false,
                slideDistance = slideDistance,
            )
        },
        modifier = modifier,
        builder = builder,
    )
}
