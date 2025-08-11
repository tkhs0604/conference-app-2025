package io.github.confsched.profile.edit

import androidx.compose.runtime.Composable
import io.github.confsched.profile.ProfileScreenContext
import io.github.droidkaigi.confsched.common.compose.rememberEventFlow
import io.github.droidkaigi.confsched.model.profile.Profile

context(screenContext: ProfileScreenContext)
@Composable
fun ProfileEditScreenRoot(
    profile: Profile?,
    onProfileCreate: () -> Unit,
) {
    val eventFlow = rememberEventFlow<ProfileEditScreenEvent>()

    val form = profileEditScreenPresenter(
        eventFlow = eventFlow,
        profile = profile,
        onProfileCreate = onProfileCreate,
    )

    ProfileEditScreen(form = form)
}
