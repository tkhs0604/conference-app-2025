package io.github.confsched.profile.card

import androidx.compose.runtime.Composable
import io.github.confsched.profile.ProfileScreenContext
import io.github.droidkaigi.confsched.common.compose.EventEffect
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.common.compose.providePresenterDefaults
import io.github.droidkaigi.confsched.model.profile.Profile

@Composable
context(screenContext: ProfileScreenContext)
fun profileCardScreenPresenter(
    eventFlow: EventFlow<ProfileCardScreenEvent>,
    profile: Profile,
): Profile = providePresenterDefaults {
    EventEffect(eventFlow) { event ->
    }

    profile
}
