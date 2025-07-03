package io.github.droidkaigi.confsched

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.SceneStrategy

@Composable
actual fun rememberBackStack(elements: NavKey): MutableList<NavKey> {
    return rememberNavBackStack(elements)
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
@Composable
actual fun sceneStrategy(): SceneStrategy<NavKey> {
    return rememberListDetailSceneStrategy()
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
actual fun listDetailSceneStrategyListPaneMetaData(): Map<String, Any> {
    return ListDetailSceneStrategy.listPane()
}

@OptIn(ExperimentalMaterial3AdaptiveApi::class)
actual fun listDetailSceneStrategyDetailPaneMetaData(): Map<String, Any> {
    return ListDetailSceneStrategy.detailPane()
}
