package io.github.droidkaigi.confsched.model.sessions

import kotlinx.serialization.Serializable

@Serializable
data class TimetableAsset(
    val videoUrl: String?,
    val slideUrl: String?,
) {
    val isAvailable: Boolean
        get() = videoUrl.isNullOrBlank().not() || slideUrl.isNullOrBlank().not()
}
