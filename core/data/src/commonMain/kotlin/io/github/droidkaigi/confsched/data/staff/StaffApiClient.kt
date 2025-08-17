package io.github.droidkaigi.confsched.data.staff

import io.github.droidkaigi.confsched.model.staff.Staff
import kotlinx.collections.immutable.PersistentList

public interface StaffApiClient {
    public suspend fun staff(): PersistentList<Staff>
}
