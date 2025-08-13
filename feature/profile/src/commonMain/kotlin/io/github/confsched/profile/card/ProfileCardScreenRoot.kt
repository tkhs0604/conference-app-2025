package io.github.confsched.profile.card

import androidx.compose.runtime.Composable
import io.github.confsched.profile.ProfileScreenContext
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow
import io.github.droidkaigi.confsched.model.profile.Profile

context(screenContext: ProfileScreenContext)
@Composable
fun ProfileCardScreenRoot(
    profile: Profile,
    onEditClick: () -> Unit,
) {
    val eventFlow = rememberEventFlow<ProfileCardScreenEvent>()

    val profile = profileCardScreenPresenter(
        eventFlow = eventFlow,
        profile = profile,
    )

    ProfileCardScreen(
        profile = profile,
        onEditClick = onEditClick,
    )
}
