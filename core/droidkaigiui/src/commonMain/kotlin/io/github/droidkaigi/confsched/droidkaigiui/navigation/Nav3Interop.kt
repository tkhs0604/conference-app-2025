package io.github.droidkaigi.confsched.droidkaigiui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.SceneStrategy

@Composable
expect fun rememberNavBackStack(vararg elements: NavKey): SnapshotStateList<NavKey>

expect fun listDetailSceneStrategyListPaneMetaData(): Map<String, Any>

expect fun listDetailSceneStrategyDetailPaneMetaData(): Map<String, Any>

@Composable
expect fun sceneStrategy(): SceneStrategy<NavKey>

@Composable
expect fun platformEntryDecorators(): List<NavEntryDecorator<*>>
