package io.github.droidkaigi.confsched.model.data

import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import kotlinx.collections.immutable.PersistentSet
import kotlinx.coroutines.flow.Flow

interface UserDataStore {
    public fun getStream(): Flow<PersistentSet<TimetableItemId>>

    // FIXME: move DataMutationContext under the model module
//    context(_: DataMutationContext)
    public suspend fun toggleFavorite(id: TimetableItemId)
}
