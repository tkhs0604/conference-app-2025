package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavKey
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow
import io.github.droidkaigi.confsched.droidkaigiui.SoilDataBoundary
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import kotlinx.serialization.Serializable
import soil.query.compose.rememberQuery
import soil.query.compose.rememberSubscription

@Serializable
data class TimetableItemDetailNavKey(val id: TimetableItemId) : NavKey

context(screenContext: TimetableItemDetailScreenContext)
@Composable
fun TimetableItemDetailScreenRoot(
    timetableItemId: TimetableItemId,
) {
    SoilDataBoundary(
        state1 = rememberQuery(screenContext.timetableItemQueryKeyFactory.create(timetableItemId)),
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
            onBookmarkClick = { isBookmarked -> eventFlow.tryEmit(TimetableItemDetailScreenEvent.Bookmark(isBookmarked)) }
        )
    }
}
