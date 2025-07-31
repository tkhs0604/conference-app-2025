package io.github.droidkaigi.confsched.about

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow

context(screenContext: AboutScreenContext)
@Composable
fun AboutScreenRoot(

) {
    val eventFlow = rememberEventFlow<AboutScreenEvent>()

    val uiState = aboutScreenPresenter(
        eventFlow = eventFlow,
    )

    AboutScreen(
        uiState = uiState,
    )
}
