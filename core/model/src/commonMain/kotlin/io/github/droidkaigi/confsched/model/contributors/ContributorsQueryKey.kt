package io.github.droidkaigi.confsched.model.contributors

import kotlinx.collections.immutable.PersistentList
import soil.query.QueryKey

typealias ContributorsQueryKey = QueryKey<PersistentList<Contributor>>
