package io.github.droidkaigi.confsched.staff

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.droidkaigiui.architecture.AppBarSize
import io.github.droidkaigi.confsched.droidkaigiui.architecture.SoilDataBoundary
import io.github.droidkaigi.confsched.droidkaigiui.architecture.SoilFallbackDefaults
import org.jetbrains.compose.resources.stringResource
import soil.query.compose.rememberQuery

context(screenContext: StaffScreenContext)
@Composable
fun StaffScreenRoot(
    onStaffItemClick: (url: String) -> Unit,
    onBackClick: () -> Unit,
) {
    SoilDataBoundary(
        state = rememberQuery(screenContext.staffQueryKey),
        fallback = SoilFallbackDefaults.appBar(
            title = stringResource(StaffRes.string.staff_title),
            onBackClick = onBackClick,
            appBarSize = AppBarSize.Medium,
        ),
    ) {
        StaffScreen(
            staff = it,
            onStaffItemClick = onStaffItemClick,
            onBackClick = onBackClick,
        )
    }
}
