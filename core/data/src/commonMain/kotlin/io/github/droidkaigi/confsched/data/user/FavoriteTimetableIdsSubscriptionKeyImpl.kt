package io.github.droidkaigi.confsched.data.user

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.model.data.FavoriteTimetableIdsSubscriptionKey
import io.github.droidkaigi.confsched.model.data.UserDataStore
import soil.query.SubscriptionId
import soil.query.buildSubscriptionKey

@ContributesBinding(DataScope::class, binding<FavoriteTimetableIdsSubscriptionKey>())
@Inject
public class FavoriteTimetableIdsSubscriptionKeyImpl(
    private val userDataStore: UserDataStore,
) : FavoriteTimetableIdsSubscriptionKey by buildSubscriptionKey(
    id = SubscriptionId("favorites"),
    subscribe = { userDataStore.getStream() }
)
