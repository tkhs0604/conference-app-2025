package io.github.droidkaigi.confsched.sessions

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.EventEffect
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.common.compose.providePresenterDefaults
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import kotlinx.collections.immutable.PersistentSet
import soil.query.compose.rememberMutation

context(screenContext: TimetableItemDetailScreenContext)
@Composable
fun timetableItemDetailScreenPresenter(
    eventFlow: EventFlow<TimetableItemDetailScreenEvent>,
    timetableItem: TimetableItem,
    favoriteTimetableItemIds: PersistentSet<TimetableItemId>,
): TimetableItemDetailScreenUiState = providePresenterDefaults {
    val favoriteTimetableItemIdMutation = rememberMutation(screenContext.favoriteTimetableItemIdMutationKey)

    EventEffect(eventFlow) { event ->
        when (event) {
            is TimetableItemDetailScreenEvent.Bookmark -> {
                favoriteTimetableItemIdMutation.mutate(timetableItem.id)
            }

            else -> {}
        }
    }

    TimetableItemDetailScreenUiState(
        timetableItem = timetableItem,
        isBookmarked = favoriteTimetableItemIds.contains(timetableItem.id),
    )
}
