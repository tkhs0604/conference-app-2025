package io.github.droidkaigi.confsched.about

import androidx.compose.runtime.Composable
import io.github.droidkaigi.confsched.common.compose.EventEffect
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.common.compose.providePresenterDefaults

@Composable
context(screenContext: AboutScreenContext)
fun aboutScreenPresenter(
    eventFlow: EventFlow<AboutScreenEvent>,
): AboutScreenUiState = providePresenterDefaults {
    EventEffect(eventFlow) { event ->
    }

    AboutScreenUiState(
        versionName = screenContext.buildConfigProvider.versionName,
    )
}
