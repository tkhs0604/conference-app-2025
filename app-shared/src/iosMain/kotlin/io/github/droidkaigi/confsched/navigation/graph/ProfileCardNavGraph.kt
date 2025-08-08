package io.github.droidkaigi.confsched.navigation.graph

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.droidkaigi.confsched.AppGraph
import io.github.droidkaigi.confsched.navigation.route.ProfileCardTabRoute

context(appGraph: AppGraph)
fun NavGraphBuilder.profileCardTabNavGraph() {
    composable<ProfileCardTabRoute> {
        // TODO
    }
}
