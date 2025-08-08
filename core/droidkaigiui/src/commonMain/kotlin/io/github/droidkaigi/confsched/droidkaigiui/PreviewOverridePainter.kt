package io.github.droidkaigi.confsched.droidkaigiui

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalInspectionMode

@Composable
inline fun previewOverridePainter(
    previewPainter: @Composable () -> Painter,
    crossinline painter: @Composable () -> Painter,
): Painter {
    if (LocalInspectionMode.current) return previewPainter()
    return painter()
}
