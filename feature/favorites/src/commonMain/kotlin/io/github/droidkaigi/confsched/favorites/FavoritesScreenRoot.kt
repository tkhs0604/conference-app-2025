package io.github.droidkaigi.confsched.favorites

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow

context(screenContext: FavoritesScreenContext)
@Composable
fun FavoritesScreenRoot() {

    val eventFlow = rememberEventFlow<FavoritesScreenEvent>()

    val uiState = favoritesScreenPresenter(
        eventFlow = eventFlow,
    )

    FavoritesScreen(
        uiState = uiState,
    )
}
