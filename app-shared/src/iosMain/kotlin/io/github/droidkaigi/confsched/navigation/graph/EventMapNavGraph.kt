package io.github.droidkaigi.confsched.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.droidkaigi.confsched.AppGraph
import io.github.droidkaigi.confsched.navigation.route.EventMapTabRoute

context(appGraph: AppGraph)
fun NavGraphBuilder.eventMapTabNavGraph() {
    composable<EventMapTabRoute> {
        // TODO
    }
}
