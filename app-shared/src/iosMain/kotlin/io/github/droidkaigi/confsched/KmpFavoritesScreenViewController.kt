package io.github.droidkaigi.confsched

import androidx.compose.ui.window.ComposeUIViewController
import io.github.droidkaigi.confsched.designsystem.theme.KaigiTheme
import io.github.droidkaigi.confsched.favorites.FavoritesScreenRoot
import io.github.droidkaigi.confsched.favorites.rememberFavoritesScreenContextRetained
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import platform.UIKit.UIViewController
import soil.query.compose.SwrClientProvider

@Suppress("UNUSED")
fun kmpFavoritesScreenViewController(
    appGraph: IosAppGraph,
    onTimetableItemClick: (TimetableItemId) -> Unit,
): UIViewController = ComposeUIViewController {
    KaigiTheme {
        SwrClientProvider(appGraph.swrClientPlus) {
            with(appGraph.rememberFavoritesScreenContextRetained()) {
                FavoritesScreenRoot(onTimetableItemClick = onTimetableItemClick)
            }
        }
    }
}
