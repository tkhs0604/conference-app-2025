package io.github.droidkaigi.confsched.droidkaigiui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator

@Composable
actual fun platformEntryDecorators(): List<NavEntryDecorator<*>> {
    return listOf(
        rememberSceneSetupNavEntryDecorator(),
        rememberSavedStateNavEntryDecorator(),
        rememberViewModelStoreNavEntryDecorator(), // This decorator is only available on Android for now
    )
}

