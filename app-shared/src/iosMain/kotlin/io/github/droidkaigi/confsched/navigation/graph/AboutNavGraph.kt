package io.github.droidkaigi.confsched.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.droidkaigi.confsched.AppGraph
import io.github.droidkaigi.confsched.about.AboutScreenRoot
import io.github.droidkaigi.confsched.about.rememberAboutScreenContextRetained
import io.github.droidkaigi.confsched.model.about.AboutItem
import io.github.droidkaigi.confsched.navigation.route.AboutRoute
import io.github.droidkaigi.confsched.navigation.route.AboutTabRoute

context(appGraph: AppGraph)
fun NavGraphBuilder.aboutTabNavGraph(
    onAboutItemClick: (AboutItem) -> Unit,
) {
    navigation<AboutTabRoute>(
        startDestination = AboutRoute,
    ) {
        aboutNavGraph(onAboutItemClick = onAboutItemClick)
    }
}

context(appGraph: AppGraph)
fun NavGraphBuilder.aboutNavGraph(
    onAboutItemClick: (AboutItem) -> Unit,
) {
    composable<AboutRoute> {
        with(appGraph.rememberAboutScreenContextRetained()) {
            AboutScreenRoot(
                onAboutItemClick = onAboutItemClick,
            )
        }
    }
}
