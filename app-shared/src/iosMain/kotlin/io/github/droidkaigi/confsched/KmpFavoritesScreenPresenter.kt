@file:Suppress("UNUSED")

package io.github.droidkaigi.confsched

import androidx.compose.runtime.Composable
import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.favorites.FavoritesScreenEvent
import io.github.droidkaigi.confsched.favorites.FavoritesScreenUiState
import io.github.droidkaigi.confsched.favorites.favoritesScreenPresenter
import io.github.droidkaigi.confsched.favorites.rememberFavoritesScreenContextRetained
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.filterNotNull
import soil.query.annotation.ExperimentalSoilQueryApi
import soil.query.compose.rememberQuery
import soil.query.compose.rememberSubscription
import soil.query.core.DataModel
import soil.query.core.Reply
import soil.query.core.combine

@OptIn(ExperimentalSoilQueryApi::class)
fun favoritesScreenPresenterStateFlow(
    iosAppGraph: IosAppGraph,
    eventFlow: EventFlow<FavoritesScreenEvent>,
): Flow<FavoritesScreenUiState> {
    return moleculeFlow(RecompositionMode.Immediate) {
        with(iosAppGraph.rememberFavoritesScreenContextRetained()) {
            soilDataBoundary(
                rememberQuery(timetableQueryKey),
                rememberSubscription(favoriteTimetableIdsSubscriptionKey)
            ) { timetable, favoriteTimetableItemIds ->
                favoritesScreenPresenter(
                    eventFlow = eventFlow,
                    timetable = timetable.copy(bookmarks = favoriteTimetableItemIds)
                )
            }
        }
    }.filterNotNull()
}

fun favoritesScreenEventFlow(): EventFlow<FavoritesScreenEvent> {
    return MutableSharedFlow(extraBufferCapacity = 20)
}

@Composable
private fun <T1, T2, RESULT> soilDataBoundary(
    state1: DataModel<T1>,
    state2: DataModel<T2>,
    combinedState: @Composable (T1, T2) -> RESULT,
): RESULT? {
    return when (val reply = Reply.combine(state1.reply, state2.reply, ::Pair)) {
        is Reply.Some -> combinedState(reply.value.first, reply.value.second)
        is Reply.None -> null
    }
}
