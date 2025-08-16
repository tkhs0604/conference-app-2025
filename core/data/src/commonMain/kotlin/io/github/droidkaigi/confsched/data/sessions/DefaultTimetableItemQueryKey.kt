package io.github.droidkaigi.confsched.data.sessions

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.common.scope.TimetableDetailScope
import io.github.droidkaigi.confsched.model.data.TimetableItemQueryKey
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import soil.query.QueryId
import soil.query.buildQueryKey

@ContributesBinding(TimetableDetailScope::class)
@Inject
public class DefaultTimetableItemQueryKey(
    timetableItemId: TimetableItemId,
    private val sessionsApiClient: SessionsApiClient,
    private val dataStore: SessionCacheDataStore,
) : TimetableItemQueryKey by buildQueryKey(
    id = QueryId(timetableItemId.value),
    fetch = {
        val response = dataStore.getCache() ?: sessionsApiClient.sessionsAllResponse()
        val timetable = response.toTimetable()
        timetable.timetableItems.first {
            it.id == timetableItemId
        }
    },
)
