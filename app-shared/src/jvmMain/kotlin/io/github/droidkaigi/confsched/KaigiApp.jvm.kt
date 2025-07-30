package io.github.droidkaigi.confsched

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember

@Composable
actual fun rememberExternalNavController(): ExternalNavController {
    return remember { JvmExternalNavController() }
}
