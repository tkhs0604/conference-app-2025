package io.github.droidkaigi.confsched

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.SceneStrategy
import androidx.navigation3.ui.SinglePaneSceneStrategy

@Composable
actual fun rememberBackStack(elements: NavKey): MutableList<NavKey> {
    return rememberSaveable { mutableStateListOf(elements) }
}

@Composable
actual fun sceneStrategy(): SceneStrategy<NavKey> {
    return SinglePaneSceneStrategy()
}

actual fun listDetailSceneStrategyListPaneMetaData(): Map<String, Any> {
    return emptyMap()
}

actual fun listDetailSceneStrategyDetailPaneMetaData(): Map<String, Any> {
    return emptyMap()
}
