package io.github.droidkaigi.confsched.data.staff

import io.github.droidkaigi.confsched.model.staff.Staff
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

public class FakeStaffApiClient : StaffApiClient {
    override suspend fun staff(): PersistentList<Staff> {
        return persistentListOf(
            Staff(
                id = "1",
                name = "Staff Member 1",
                icon = "https://example.com/icon1.png",
                profileUrl = "https://github.com/staff1",
            ),
            Staff(
                id = "2",
                name = "Staff Member 2",
                icon = "https://example.com/icon2.png",
                profileUrl = "https://twitter.com/staff2",
            ),
            Staff(
                id = "3",
                name = "Staff Member 3",
                icon = "https://example.com/icon3.png",
                profileUrl = null,
            ),
        )
    }
}