package io.github.droidkaigi.confsched.droidkaigiui

import androidx.compose.ui.unit.dp

/**
 * The threshold values for each window size class are not publicly accessible,
 * so we define them here for reference.
 *
 * As Navigation 3 supports two-pane layouts, we sometimes need to determine
 * the layout manually by comparing the available size with each window size class.
 */
object KaigiWindowSizeClassConstants {
    val WindowWidthSizeClassMediumMinWidth = 600.dp
}
