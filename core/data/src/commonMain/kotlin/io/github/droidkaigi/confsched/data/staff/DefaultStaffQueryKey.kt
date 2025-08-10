package io.github.droidkaigi.confsched.data.staff

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.model.staff.StaffQueryKey
import soil.query.QueryId
import soil.query.buildQueryKey

@ContributesBinding(DataScope::class)
@Inject
public class DefaultStaffQueryKey(
    private val staffApiClient: StaffApiClient,
) : StaffQueryKey by buildQueryKey(
    id = QueryId("staff"),
    fetch = { staffApiClient.staff() }
)