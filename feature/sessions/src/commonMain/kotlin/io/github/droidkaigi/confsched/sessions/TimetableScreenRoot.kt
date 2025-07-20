package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow
import io.github.droidkaigi.confsched.droidkaigiui.SoilDataBoundary
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import soil.query.compose.rememberSubscription

context(screenContext: TimetableScreenContext)
@Composable
fun TimetableScreenRoot(
    onSearchClick: () -> Unit,
    onTimetableItemClick: (TimetableItemId) -> Unit,
) {
    SoilDataBoundary(
        state1 = rememberSubscription(screenContext.timetableSubscriptionKey),
        state2 = rememberSubscription(screenContext.favoriteTimetableIdsSubscriptionKey),
        errorFallback = { TimetableScreenErrorFallback(it, onSearchClick) }
    ) { timetable, favoriteTimetableItemIds ->
        val eventFlow = rememberEventFlow<TimetableScreenEvent>()

        val uiState = timetableScreenPresenter(
            eventFlow = eventFlow,
            timetable = timetable.copy(bookmarks = favoriteTimetableItemIds),
        )

        TimetableScreen(
            uiState = uiState,
            onSearchClick = onSearchClick,
            onTimetableItemClick = onTimetableItemClick,
            onBookmarkClick = { sessionId, isBookmarked ->
                eventFlow.tryEmit(TimetableScreenEvent.Bookmark(sessionId, isBookmarked))
            },
            onTimetableUiChangeClick = { eventFlow.tryEmit(TimetableScreenEvent.UiTypeChange) },
        )
    }
}

