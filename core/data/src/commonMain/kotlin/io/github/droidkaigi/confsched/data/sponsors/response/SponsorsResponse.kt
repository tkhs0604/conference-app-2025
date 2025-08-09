package io.github.droidkaigi.confsched.data.sponsors.response

import kotlinx.serialization.Serializable

@Serializable
public data class SponsorsResponse(
    val sponsors: List<SponsorResponse> = emptyList(),
)

@Serializable
public data class SponsorResponse(
    val id: String,
    val name: String,
    val logo: String,
    val plan: String,
    val link: String,
)