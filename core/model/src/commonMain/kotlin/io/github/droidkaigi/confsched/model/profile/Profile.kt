package io.github.droidkaigi.confsched.model.profile

import kotlinx.serialization.Serializable

@Serializable
data class Profile(
    val name: String,
    val occupation: String,
    val link: String,
    val theme: String,
) {
    override fun toString(): String {
        return """
            Profile(
                name='$name',
                occupation='$occupation',
                link='$link',
                theme='$theme'
            )
        """.trimIndent()
    }
}
