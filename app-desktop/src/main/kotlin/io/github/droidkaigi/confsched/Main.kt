package io.github.droidkaigi.confsched

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigationevent.NavigationEventDispatcher
import androidx.navigationevent.NavigationEventDispatcherOwner
import androidx.navigationevent.compose.LocalNavigationEventDispatcherOwner
import dev.zacsweers.metro.createGraph
import kotlinx.coroutines.delay

fun main() = application {
    val graph: AppGraph = createGraph()

    Window(
        onCloseRequest = ::exitApplication,
        state = rememberWindowState(
            width = 1200.dp,
            height = 800.dp,
        )
    ) {
        // Workaround for "component must be showing on the screen to determine its location" error
        val visible by produceState(false) {
            delay(1000L)
            value = true
        }

        if (visible) {
            // workaround for missing CompositionLocalProvider
            CompositionLocalProvider(LocalLifecycleOwner provides FakeLifecycleOwner()) {
                CompositionLocalProvider(LocalNavigationEventDispatcherOwner provides FakeNavigationEventDispatcherOwner()) {
                    with(graph) {
                        KaigiApp()
                    }
                }
            }
        }
    }
}

private class FakeNavigationEventDispatcherOwner : NavigationEventDispatcherOwner {
    override val navigationEventDispatcher: NavigationEventDispatcher get() = NavigationEventDispatcher()
}

private class FakeLifecycleOwner : LifecycleOwner {
    override val lifecycle: Lifecycle = LifecycleRegistry(this)
}
