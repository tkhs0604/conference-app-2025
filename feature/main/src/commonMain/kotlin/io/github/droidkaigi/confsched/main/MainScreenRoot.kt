package io.github.droidkaigi.confsched.main

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow

@Composable
fun MainScreenRoot(
    eventFlow: EventFlow<MainScreenEvent> = rememberEventFlow(),
    uiState: MainScreenUiState = mainScreenPresenter(eventFlow),
) {
}

sealed interface MainScreenEvent

data object MainScreenUiState

@Composable
fun mainScreenPresenter(
    eventFlow: EventFlow<MainScreenEvent> = rememberEventFlow(),
): MainScreenUiState {
    return MainScreenUiState
}
