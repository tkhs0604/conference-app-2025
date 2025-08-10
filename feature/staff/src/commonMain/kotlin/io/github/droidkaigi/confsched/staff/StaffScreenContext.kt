package io.github.droidkaigi.confsched.staff

import dev.zacsweers.metro.AppScope
import dev.zacsweers.metro.ContributesGraphExtension
import io.github.droidkaigi.confsched.common.scope.StaffScope
import io.github.droidkaigi.confsched.context.ScreenContext
import io.github.droidkaigi.confsched.model.staff.StaffQueryKey

@ContributesGraphExtension(StaffScope::class)
interface StaffScreenContext : ScreenContext {
    val staffQueryKey: StaffQueryKey

    @ContributesGraphExtension.Factory(AppScope::class)
    fun interface Factory {
        fun createStaffScreenContext(): StaffScreenContext
    }
}
