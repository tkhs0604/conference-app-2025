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
public class TimetableItemQueryKeyImpl(
    override val timetableItemId: TimetableItemId,
    private val sessionsApiClient: SessionsApiClient,
) : TimetableItemQueryKey, QueryKey<TimetableItem> by buildQueryKey(
    id = QueryId(timetableItemId.value),
    fetch = {
        val timetable = sessionsApiClient.sessionsAllResponse().toTimetable()
        timetable.timetableItems.first {
            it.id == timetableItemId
        }
    }
)
