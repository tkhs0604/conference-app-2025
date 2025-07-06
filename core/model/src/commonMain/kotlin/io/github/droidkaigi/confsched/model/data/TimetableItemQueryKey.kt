package io.github.droidkaigi.confsched.model.data

import io.github.droidkaigi.confsched.model.sessions.TimetableDetail
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import soil.query.QueryKey

interface TimetableItemQueryKey : QueryKey<TimetableDetail> {
    val timetableItemId: TimetableItemId

    interface Factory {
        fun create(timetableItemId: TimetableItemId): TimetableItemQueryKey
    }
}
