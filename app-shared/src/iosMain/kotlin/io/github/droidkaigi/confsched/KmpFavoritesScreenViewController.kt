package io.github.droidkaigi.confsched

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.ComposeUIViewController
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.droidkaigiui.compositionlocal.LocalBottomNavigationBarsPadding
import io.github.droidkaigi.confsched.favorites.FavoritesScreenRoot
import io.github.droidkaigi.confsched.favorites.rememberFavoritesScreenContextRetained
import io.github.droidkaigi.confsched.model.sessions.Timetable
import io.github.droidkaigi.confsched.model.sessions.TimetableItemWithFavorite
import platform.UIKit.UIViewController
import soil.query.compose.SwrClientProvider

@Suppress("UNUSED")
fun kmpFavoritesScreenViewController(
    appGraph: IosAppGraph,
    onTimetableItemClick: (TimetableItemWithFavorite) -> Unit,
): UIViewController = ComposeUIViewController {
    val timetableItems by appGraph.sessionsRepository.timetableFlow().collectAsStateWithLifecycle(Timetable())
    KaigiTheme {
        SwrClientProvider(appGraph.swrClientPlus) {
            // Add a constant bottom padding (112.dp) to avoid overlapping with the bottom navigation bar on iOS (value from Figma design).
            CompositionLocalProvider(LocalBottomNavigationBarsPadding provides PaddingValues(bottom = 112.dp)) {
                with(appGraph.rememberFavoritesScreenContextRetained()) {
                    FavoritesScreenRoot(
                        onTimetableItemClick = { timetableItemId ->
                            // iOS navigation requires the full timetable item data
                            timetableItems.contents
                                .firstOrNull { it.timetableItem.id == timetableItemId }
                                ?.let(onTimetableItemClick)
                        }
                    )
                }
            }
        }
    }
}
