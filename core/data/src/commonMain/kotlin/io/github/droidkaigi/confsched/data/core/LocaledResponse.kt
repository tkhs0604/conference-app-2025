package io.github.droidkaigi.confsched.data.core

import kotlinx.serialization.Serializable

@Serializable
public data class LocaledResponse(
    val ja: String,
    val en: String,
)
