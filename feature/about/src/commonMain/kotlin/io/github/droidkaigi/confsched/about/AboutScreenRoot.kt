package io.github.droidkaigi.confsched.about

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow
import io.github.droidkaigi.confsched.model.about.AboutItem

context(screenContext: AboutScreenContext)
@Composable
fun AboutScreenRoot(
    onAboutItemClick: (AboutItem) -> Unit,
) {
    val eventFlow = rememberEventFlow<AboutScreenEvent>()

    val uiState = aboutScreenPresenter(
        eventFlow = eventFlow,
    )

    AboutScreen(
        uiState = uiState,
        onAboutItemClick = onAboutItemClick,
    )
}
