package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow
import io.github.droidkaigi.confsched.droidkaigiui.SoilDataBoundary
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import soil.query.compose.rememberQuery
import soil.query.compose.rememberSubscription

context(screenContext: TimetableItemDetailScreenContext)
@Composable
fun TimetableItemDetailScreenRoot(
    onBackClick: () -> Unit,
    onAddCalendarClick: (TimetableItem) -> Unit,
    onShareClick: (TimetableItem) -> Unit,
) {
    SoilDataBoundary(
        state1 = rememberQuery(screenContext.timetableItemQueryKey),
        state2 = rememberSubscription(screenContext.favoriteTimetableIdsSubscriptionKey),
        errorFallback = {
            TimetableItemDetailScreenErrorFallback(
                errorBoundaryContext = it,
                onBackClick = onBackClick,
            )
        }
    ) { timetableItem, favoriteTimetableItemIds ->
        val eventFlow = rememberEventFlow<TimetableItemDetailScreenEvent>()

        val uiState = timetableItemDetailScreenPresenter(
            eventFlow = eventFlow,
            timetableItem = timetableItem,
            favoriteTimetableItemIds = favoriteTimetableItemIds,
        )

        TimetableItemDetailScreen(
            uiState = uiState,
            onBackClick = onBackClick,
            onBookmarkClick = { isBookmarked -> eventFlow.tryEmit(TimetableItemDetailScreenEvent.Bookmark(isBookmarked)) },
            onAddCalendarClick = onAddCalendarClick,
            onShareClick = onShareClick,
            onLanguageSelect = { lang -> eventFlow.tryEmit(TimetableItemDetailScreenEvent.LanguageSelect(lang)) },
        )
    }
}
