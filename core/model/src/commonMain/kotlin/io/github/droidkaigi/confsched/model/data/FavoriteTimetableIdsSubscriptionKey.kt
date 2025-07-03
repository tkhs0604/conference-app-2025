package io.github.droidkaigi.confsched.model.data

import io.github.droidkaigi.confsched.model.sessions.TimetableItemId
import kotlinx.collections.immutable.PersistentSet
import soil.query.SubscriptionKey

typealias FavoriteTimetableIdsSubscriptionKey = SubscriptionKey<PersistentSet<TimetableItemId>>
