package io.github.droidkaigi.confsched.contributors

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.droidkaigiui.SoilDataBoundary
import soil.query.compose.rememberQuery

context(screenContext: ContributorsScreenContext)
@Composable
fun ContributorsScreenRoot(
    onBackClick: () -> Unit,
    onContributorClick: (String) -> Unit,
) {
    SoilDataBoundary(
        state = rememberQuery(screenContext.contributorsQueryKey),
    ) {
        ContributorsScreen(
            contributors = it,
            onBackClick = onBackClick,
            onContributorItemClick = onContributorClick,
        )
    }
}
