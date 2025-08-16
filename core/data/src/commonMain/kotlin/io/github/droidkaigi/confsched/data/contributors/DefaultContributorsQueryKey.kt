package io.github.droidkaigi.confsched.data.contributors

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.model.contributors.ContributorsQueryKey
import soil.query.QueryId
import soil.query.buildQueryKey

@ContributesBinding(DataScope::class)
@Inject
public class DefaultContributorsQueryKey(
    private val contributorsApiClient: ContributorsApiClient,
) : ContributorsQueryKey by buildQueryKey(
    id = QueryId("contributors"),
    fetch = { contributorsApiClient.contributors() },
)
