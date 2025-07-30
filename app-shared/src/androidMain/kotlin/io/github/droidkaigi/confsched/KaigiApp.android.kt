package io.github.droidkaigi.confsched

import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.ui.SceneStrategy

@Composable
actual fun rememberExternalNavController(): ExternalNavController {
    val context = LocalContext.current
    return remember(context) {
        AndroidExternalNavController(
            context = context,
        )
    }
}
