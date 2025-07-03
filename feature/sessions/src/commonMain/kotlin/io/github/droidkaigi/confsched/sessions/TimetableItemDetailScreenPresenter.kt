package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId

@Composable
fun timetableItemDetailScreenPresenter(
    eventFlow: EventFlow<TimetableItemDetailScreenEvent>,
    timetableItemId: TimetableItemId,
): TimetableItemDetailScreenUiState {
    return TimetableItemDetailScreenUiState()
}
