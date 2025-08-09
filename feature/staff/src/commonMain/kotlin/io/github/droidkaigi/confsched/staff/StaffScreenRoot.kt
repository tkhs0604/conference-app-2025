package io.github.droidkaigi.confsched.staff

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.droidkaigiui.SoilDataBoundary
import soil.query.compose.rememberQuery

context(screenContext: StaffScreenContext)
@Composable
fun StaffScreenRoot(
    onStaffItemClick: (url: String) -> Unit,
    onBackClick: () -> Unit,
) {
    SoilDataBoundary(
        state = rememberQuery(screenContext.staffQueryKey),
    ) {
        StaffScreen(
            staff = it,
            onStaffItemClick = onStaffItemClick,
            onBackClick = onBackClick,
        )
    }
}
