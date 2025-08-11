package io.github.droidkaigi.confsched.model.sponsors

import kotlinx.collections.immutable.PersistentList
import soil.query.QueryKey

typealias SponsorsQueryKey = QueryKey<PersistentList<Sponsor>>
