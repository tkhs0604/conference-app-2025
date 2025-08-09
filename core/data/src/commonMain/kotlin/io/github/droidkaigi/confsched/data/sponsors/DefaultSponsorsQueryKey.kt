package io.github.droidkaigi.confsched.data.sponsors

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.model.sponsors.SponsorsQueryKey
import soil.query.QueryId
import soil.query.buildQueryKey

@ContributesBinding(DataScope::class)
@Inject
public class DefaultSponsorsQueryKey(
    private val sponsorsApiClient: SponsorsApiClient,
) : SponsorsQueryKey by buildQueryKey(
    id = QueryId("sponsors"),
    fetch = { sponsorsApiClient.sponsors() }
)