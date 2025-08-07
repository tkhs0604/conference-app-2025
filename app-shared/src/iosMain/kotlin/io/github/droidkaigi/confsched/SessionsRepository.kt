package io.github.droidkaigi.confsched

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableIdsSubscriptionKey
import io.github.droidkaigi.confsched.model.data.TimetableQueryKey
import io.github.droidkaigi.confsched.model.sessions.Timetable
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import kotlinx.collections.immutable.PersistentSet
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.timeout
import soil.query.SwrClientPlus
import soil.query.annotation.ExperimentalSoilQueryApi
import soil.query.compose.QuerySuccessObject
import soil.query.compose.SubscriptionSuccessObject
import soil.query.compose.rememberQuery
import soil.query.compose.rememberSubscription
import kotlin.time.Duration.Companion.milliseconds

@Inject
class SessionsRepository(
    private val swrClient: SwrClientPlus,
    private val timetableQueryKey: TimetableQueryKey,
    private val favoriteTimetableIdsSubscriptionKey: FavoriteTimetableIdsSubscriptionKey,
) {
    @OptIn(ExperimentalSoilQueryApi::class, FlowPreview::class)
    fun timetableFlow(): Flow<Timetable> = moleculeFlow(RecompositionMode.Immediate) {
        val timetableQuery = rememberQuery(
            key = timetableQueryKey,
            client = swrClient,
        )
        val favoriteTimetableIdsSubscription = rememberSubscription(
            key = favoriteTimetableIdsSubscriptionKey,
            client = swrClient,
        )

        if (
            timetableQuery is QuerySuccessObject<Timetable> &&
            favoriteTimetableIdsSubscription is SubscriptionSuccessObject<PersistentSet<TimetableItemId>>
        ) {
            val timetable = timetableQuery.data
            val favoriteTimetableIds = favoriteTimetableIdsSubscription.data

            timetable.copy(bookmarks = favoriteTimetableIds)
        } else {
            null
        }
    }
        .filterNotNull()
        .timeout(5000.milliseconds)
        // Errors thrown inside flow can't be caught on iOS side, so we catch it here.
        .catch { emit(Timetable()) }
}
