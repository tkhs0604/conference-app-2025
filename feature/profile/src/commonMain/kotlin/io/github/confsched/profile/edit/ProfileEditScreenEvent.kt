package io.github.confsched.profile.edit

import io.github.droidkaigi.confsched.model.profile.Profile

sealed interface ProfileEditScreenEvent {
    data class Create(val profile: Profile) : ProfileEditScreenEvent
}
