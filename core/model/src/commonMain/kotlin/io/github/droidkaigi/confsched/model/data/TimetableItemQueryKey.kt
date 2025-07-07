package io.github.droidkaigi.confsched.model.data

import io.github.droidkaigi.confsched.model.sessions.TimetableItem
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import soil.query.QueryKey

interface TimetableItemQueryKey : QueryKey<TimetableItem> {
    val timetableItemId: TimetableItemId
}
