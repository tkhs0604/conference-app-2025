package io.github.confsched.profile.edit

import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import io.github.confsched.profile.ProfileScreenContext
import io.github.droidkaigi.confsched.common.compose.EventEffect
import io.github.droidkaigi.confsched.common.compose.EventFlow
import io.github.droidkaigi.confsched.common.compose.SafeLaunchedEffect
import io.github.droidkaigi.confsched.common.compose.providePresenterDefaults
import io.github.droidkaigi.confsched.designsystem.theme.ProfileTheme
import io.github.droidkaigi.confsched.model.profile.Profile
import io.github.droidkaigi.confsched.profile.ProfileRes
import io.github.droidkaigi.confsched.profile.add_validate_format
import io.github.droidkaigi.confsched.profile.enter_validate_format
import io.github.droidkaigi.confsched.profile.image
import io.github.droidkaigi.confsched.profile.link
import io.github.droidkaigi.confsched.profile.nickname
import io.github.droidkaigi.confsched.profile.occupation
import io.github.droidkaigi.confsched.profile.url_is_invalid
import io.github.takahirom.rin.rememberRetained
import org.jetbrains.compose.resources.stringResource
import soil.query.compose.rememberMutation

context(screenContext: ProfileScreenContext)
@Composable
fun profileEditScreenPresenter(
    eventFlow: EventFlow<ProfileEditScreenEvent>,
    profile: Profile?,
): ProfileEditScreenUiState = providePresenterDefaults {
    val profileMutation = rememberMutation(screenContext.profileMutationKey)
    val emptyNicknameErrorString = stringResource(
        ProfileRes.string.enter_validate_format,
        stringResource(ProfileRes.string.nickname),
    )
    val emptyOccupationErrorString = stringResource(
        ProfileRes.string.enter_validate_format,
        stringResource(ProfileRes.string.occupation),
    )
    val emptyLinkErrorString = stringResource(
        ProfileRes.string.enter_validate_format,
        stringResource(ProfileRes.string.link),
    )
    val invalidLinkErrorString = stringResource(
        ProfileRes.string.url_is_invalid,
    )
    val emptyImageErrorString = stringResource(
        ProfileRes.string.add_validate_format,
        stringResource(ProfileRes.string.image),
    )

    var name: String? by rememberRetained { mutableStateOf(profile?.name) }
    var occupation: String? by rememberRetained { mutableStateOf(profile?.occupation) }
    var link: String? by rememberRetained { mutableStateOf(profile?.link) }
    var theme: ProfileTheme by rememberRetained {
        val theme = profile?.theme?.let { ProfileTheme.fromName(it) } ?: ProfileTheme.Iguana
        mutableStateOf(theme)
    }
    var error: ProfileEditError by rememberRetained { mutableStateOf(ProfileEditError()) }
    var created by rememberRetained { mutableStateOf(false) }
    val uiState by remember {
        derivedStateOf {
            ProfileEditScreenUiState(
                name = name,
                occupation = occupation,
                link = link,
                theme = theme,
                error = error,
                created = created,
            )
        }
    }

    SafeLaunchedEffect(uiState) {
        uiState.profile?.let { profile ->
            profileMutation.mutate(profile)
            created = true
        }
    }

    EventEffect(eventFlow) { event ->
        when (event) {
            is ProfileEditScreenEvent.ChangeLink -> {
                link = event.link
            }

            is ProfileEditScreenEvent.ChangeName -> {
                name = event.name
            }

            is ProfileEditScreenEvent.ChangeOccupation -> {
                occupation = event.occupation
            }

            is ProfileEditScreenEvent.ChangeTheme -> {
                theme = event.theme
            }

            ProfileEditScreenEvent.Create -> {
                // debug
                name = "sample"
                link = "https://example.com"
                occupation = "Android Developer"
                // debug
                error = error.copy(
                    nicknameError = if (uiState.isValidName()) null else emptyNicknameErrorString,
                    occupationError = if (uiState.isValidOccupation()) null else emptyOccupationErrorString,
                    linkError = when {
                        uiState.isValidLink() -> null
                        uiState.link.isNullOrEmpty() -> emptyLinkErrorString
                        else -> invalidLinkErrorString
                    },
                )
            }
        }
    }

    uiState
}
