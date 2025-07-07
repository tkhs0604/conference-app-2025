package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow
import io.github.droidkaigi.confsched.droidkaigiui.SoilDataBoundary
import soil.query.compose.rememberQuery
import soil.query.compose.rememberSubscription

context(screenContext: TimetableItemDetailScreenContext)
@Composable
fun TimetableItemDetailScreenRoot(
    onBackClick: () -> Unit,
) {
    SoilDataBoundary(
        state1 = rememberQuery(screenContext.timetableItemQueryKey),
        state2 = rememberSubscription(screenContext.favoriteTimetableIdsSubscriptionKey),
        errorFallback = {}
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
            onBookmarkClick = { isBookmarked -> eventFlow.tryEmit(TimetableItemDetailScreenEvent.Bookmark(isBookmarked)) }
        )
    }
}
