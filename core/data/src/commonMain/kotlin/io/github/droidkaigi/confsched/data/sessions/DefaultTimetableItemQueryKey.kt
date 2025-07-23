package io.github.droidkaigi.confsched.data.sessions

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import io.github.droidkaigi.confsched.common.scope.TimetableDetailScope
import io.github.droidkaigi.confsched.model.data.TimetableItemQueryKey
import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import soil.query.QueryId
import soil.query.QueryKey
import soil.query.buildQueryKey

@ContributesBinding(TimetableDetailScope::class, binding<TimetableItemQueryKey>())
@Inject
public class DefaultTimetableItemQueryKey(
    override val timetableItemId: TimetableItemId,
    private val sessionsApiClient: SessionsApiClient,
    private val dataStore: SessionCacheDataStore,
) : TimetableItemQueryKey, QueryKey<TimetableItem> by buildQueryKey(
    id = QueryId(timetableItemId.value),
    fetch = {
        val response = dataStore.getCache() ?: sessionsApiClient.sessionsAllResponse()
        val timetable = response.toTimetable()
        timetable.timetableItems.first {
            it.id == timetableItemId
        }
    }
)
