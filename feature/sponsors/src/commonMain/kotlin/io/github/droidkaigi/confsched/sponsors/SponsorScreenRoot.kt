package io.github.droidkaigi.confsched.sponsors

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.droidkaigiui.architecture.SoilDataBoundary
import soil.query.compose.rememberQuery

@Composable
context(screenContext: SponsorsScreenContext)
fun SponsorScreenRoot(
    onBackClick: () -> Unit,
    onSponsorClick: (String) -> Unit,
) {
    SoilDataBoundary(
        state = rememberQuery(screenContext.sponsorsQueryKey),
    ) {
        SponsorsScreen(
            sponsors = it,
            onBackClick = onBackClick,
            onSponsorsItemClick = onSponsorClick,
        )
    }
}
