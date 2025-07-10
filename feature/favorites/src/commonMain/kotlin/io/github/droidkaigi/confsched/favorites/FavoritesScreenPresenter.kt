package io.github.droidkaigi.confsched.favorites

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.EventEffect
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.common.compose.providePresenterDefaults

context(screenContext: FavoritesScreenContext)
@Composable
fun favoritesScreenPresenter(
    eventFlow: EventFlow<FavoritesScreenEvent>,
): FavoritesScreenUiState = providePresenterDefaults {

    EventEffect(eventFlow) { event ->

    }

    FavoritesScreenUiState
}
