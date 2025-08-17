package io.github.droidkaigi.confsched.data.user

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableItemIdMutationKey
import soil.query.MutationId
import soil.query.buildMutationKey

@ContributesBinding(DataScope::class)
@Inject
public class DefaultFavoriteTimetableItemIdMutationKey(
    private val userDataStore: UserDataStore,
) : FavoriteTimetableItemIdMutationKey by buildMutationKey(
    id = MutationId("favorite_timetable_item_id_mutation_key"),
    mutate = { userDataStore.toggleFavorite(it) },
)
