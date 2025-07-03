package io.github.droidkaigi.confsched.data.contributors

import io.github.droidkaigi.confsched.model.contributors.Contributor
import kotlinx.collections.immutable.PersistentList

public interface ContributorsApiClient {

    public suspend fun contributors(): PersistentList<Contributor>
}
