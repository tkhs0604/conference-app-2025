package io.github.droidkaigi.confsched.data.core

import kotlinx.serialization.Serializable

@Serializable
public data class RoomResponse(
    val name: LocaledResponse,
    val id: Int,
    val sort: Int,
)
