package io.github.droidkaigi.confsched.data.user

import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableItemIdMutationKey
import io.github.droidkaigi.confsched.model.data.UserDataStore
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import soil.query.MutationId
import soil.query.MutationKey
import soil.query.buildMutationKey

@Inject
public class FavoriteTimetableItemIdMutationKeyImpl(
    private val userDataStore: UserDataStore
) : FavoriteTimetableItemIdMutationKey, MutationKey<Unit, TimetableItemId> by buildMutationKey(
    id = MutationId("favorite_timetable_item_id_mutation_key"),
    mutate = { userDataStore.toggleFavorite(it) }
)
