package io.github.droidkaigi.confsched.data.user

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableItemIdMutationKey
import io.github.droidkaigi.confsched.model.data.UserDataStore
import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import soil.query.MutationId
import soil.query.MutationKey
import soil.query.buildMutationKey

@ContributesBinding(AppScope::class, binding<FavoriteTimetableItemIdMutationKey>())
@Inject
public class FavoriteTimetableItemIdMutationKeyImpl(
    private val userDataStore: UserDataStore
) : FavoriteTimetableItemIdMutationKey, MutationKey<Unit, TimetableItemId> by buildMutationKey(
    id = MutationId("favorite_timetable_item_id_mutation_key"),
    mutate = { userDataStore.toggleFavorite(it) }
)
