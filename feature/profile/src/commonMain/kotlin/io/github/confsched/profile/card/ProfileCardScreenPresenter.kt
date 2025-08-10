package io.github.confsched.profile.card

import androidx.compose.runtime.Composable
import io.github.confsched.profile.ProfileScreenContext
import io.github.droidkaigi.confsched.common.compose.EventEffect
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.common.compose.providePresenterDefaults
import io.github.droidkaigi.confsched.designsystem.theme.ProfileTheme
import io.github.droidkaigi.confsched.model.profile.Profile

context(screenContext: ProfileScreenContext)
@Composable
fun profileCardScreenPresenter(
    eventFlow: EventFlow<ProfileCardScreenEvent>,
    profile: Profile,
): ProfileCardScreenUiState = providePresenterDefaults {
    EventEffect(eventFlow) { event ->
    }

    profile.toUiState()
}

private fun Profile.toUiState(): ProfileCardScreenUiState {
    return ProfileCardScreenUiState(
        name = name,
        occupation = occupation,
        link = link,
        theme = ProfileTheme.fromName(theme),
    )
}
