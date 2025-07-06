package io.github.droidkaigi.confsched.data.sessions

import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.model.data.TimetableItemQueryKey
import io.github.droidkaigi.confsched.model.sessions.TimetableDetail
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import soil.query.QueryId
import soil.query.QueryKey
import soil.query.buildQueryKey

@Inject
public class TimetableItemQueryKeyImpl(
    override val timetableItemId: TimetableItemId,
    private val sessionsApiClient: SessionsApiClient,
) : TimetableItemQueryKey, QueryKey<TimetableDetail> by buildQueryKey(
    id = QueryId(timetableItemId.value),
    fetch = {
        val timetable = sessionsApiClient.sessionsAllResponse().toTimetable()
        val timetableItem = timetable.timetableItems.first {
            it.id == timetableItemId
        }
        TimetableDetail(
            timetableItem = timetableItem,
            isBookmarked = timetable.bookmarks.contains(timetableItemId)
        )
    }
) {

    public class Factory(
        private val sessionsApiClient: SessionsApiClient
    ) : TimetableItemQueryKey.Factory {
        override fun create(timetableItemId: TimetableItemId): TimetableItemQueryKey {
           return TimetableItemQueryKeyImpl(
                timetableItemId = timetableItemId,
                sessionsApiClient = sessionsApiClient
            )
        }
    }
}
