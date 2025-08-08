package io.github.droidkaigi.confsched

import androidx.compose.ui.window.ComposeUIViewController
import platform.UIKit.UIViewController

@Suppress("UNUSED")
fun kaigiAppViewController(appGraph: AppGraph): UIViewController = ComposeUIViewController {
    with(appGraph) {
        KaigiApp()
    }
}
