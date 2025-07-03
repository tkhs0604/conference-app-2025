package io.github.droidkaigi.confsched.droidkaigiui

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator

@Composable
actual fun platformEntryDecorators(): List<NavEntryDecorator<*>> {
    return listOf(
        rememberSceneSetupNavEntryDecorator(),
        rememberSavedStateNavEntryDecorator(),
    )
}
