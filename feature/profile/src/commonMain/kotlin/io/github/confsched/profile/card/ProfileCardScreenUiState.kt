package io.github.confsched.profile.card

import io.github.droidkaigi.confsched.designsystem.theme.ProfileTheme

data class ProfileCardScreenUiState(
    val name: String,
    val occupation: String,
    val link: String,
    val theme: ProfileTheme,
) {
    override fun toString(): String {
        return """
ProfileCardScreenUiState(
    name=$name,
    occupation=$occupation,
    link=$link,
    theme=$theme
)
        """.trimIndent()
    }
}
