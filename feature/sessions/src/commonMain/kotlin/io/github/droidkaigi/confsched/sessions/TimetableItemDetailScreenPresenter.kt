package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.EventEffect
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.common.compose.providePresenterDefaults
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId

context(screenContext: TimetableItemDetailScreenContext)
@Composable
fun timetableItemDetailScreenPresenter(
    eventFlow: EventFlow<TimetableItemDetailScreenEvent>,
    timetableItemId: TimetableItemId,
): TimetableItemDetailScreenUiState = providePresenterDefaults {

    EventEffect(eventFlow) { event ->
        when (event) {

            else -> {}
        }
    }

    TimetableItemDetailScreenUiState()
}
