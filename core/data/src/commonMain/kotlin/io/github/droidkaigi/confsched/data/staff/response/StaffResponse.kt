package io.github.droidkaigi.confsched.data.staff.response

import kotlinx.serialization.Serializable

@Serializable
public data class StaffResponse(
    val staff: List<StaffItemResponse> = emptyList(),
)

@Serializable
public data class StaffItemResponse(
    val id: String,
    val name: String,
    val icon: String,
    val profileUrl: String? = null,
)