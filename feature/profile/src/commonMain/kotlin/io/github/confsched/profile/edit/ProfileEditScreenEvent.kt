package io.github.confsched.profile.edit

import io.github.droidkaigi.confsched.designsystem.theme.ProfileTheme

sealed interface ProfileEditScreenEvent {
    data class ChangeName(val name: String) : ProfileEditScreenEvent
    data class ChangeOccupation(val occupation: String) : ProfileEditScreenEvent
    data class ChangeLink(val link: String) : ProfileEditScreenEvent
    data class ChangeTheme(val theme: ProfileTheme) : ProfileEditScreenEvent
    data object Create : ProfileEditScreenEvent
}
