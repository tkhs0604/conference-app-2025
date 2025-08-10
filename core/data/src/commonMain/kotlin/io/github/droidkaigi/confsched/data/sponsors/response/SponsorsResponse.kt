package io.github.droidkaigi.confsched.data.sponsors.response

import io.github.droidkaigi.confsched.model.sponsors.Plan
import kotlinx.serialization.Serializable

@Serializable
public data class SponsorsResponse(
    val status: String,
    val sponsors: List<SponsorResponse>,
)

@Serializable
public data class SponsorResponse(
    val name: String,
    val logo: String,
    val plan: String,
    val link: String,
)
