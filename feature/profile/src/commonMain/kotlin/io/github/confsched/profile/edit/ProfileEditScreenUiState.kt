package io.github.confsched.profile.edit

import io.github.droidkaigi.confsched.designsystem.theme.ProfileTheme
import io.github.droidkaigi.confsched.model.profile.Profile

data class ProfileEditScreenUiState(
    val name: String?,
    val occupation: String?,
    val link: String?,
    val theme: ProfileTheme,
    val error: ProfileEditError,
    val created: Boolean = false,
) {
    fun isValidName(): Boolean {
        return !name.isNullOrBlank()
    }

    fun isValidOccupation(): Boolean {
        return !occupation.isNullOrBlank()
    }

    fun isValidLink(): Boolean {
        val linkFormat = Regex("^(?:https?://)?(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z0-9-]{2,}(?:/\\S*)?$")
        return !link.isNullOrBlank() && link.matches(linkFormat)
    }

    val profile: Profile?
        get() = if (isValidName() && isValidOccupation() && isValidLink() && error.isEmpty()) {
            Profile(
                name = name!!,
                occupation = occupation!!,
                link = link!!,
                theme = theme::class.simpleName!!,
            )
        } else {
            null
        }

    override fun toString(): String {
        return """ProfileEditScreenUiState(
    name=$name,
    occupation=$occupation,
    link=$link,
    theme=$theme,
    error=$error,
    created=$created
)"""
    }
}

data class ProfileEditError(
    val nicknameError: String? = null,
    val occupationError: String? = null,
    val linkError: String? = null,
    val imageError: String? = null,
) {
    fun isEmpty(): Boolean {
        return nicknameError == null && occupationError == null && linkError == null && imageError == null
    }

    override fun toString(): String {
        return """ProfileEditError(
        nicknameError=$nicknameError,
        occupationError=$occupationError,
        linkError=$linkError,
        imageError=$imageError
    )"""
    }
}
