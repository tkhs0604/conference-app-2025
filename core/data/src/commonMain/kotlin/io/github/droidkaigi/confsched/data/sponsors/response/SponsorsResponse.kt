package io.github.droidkaigi.confsched.data.sponsors.response

import kotlinx.serialization.Serializable

@Serializable
public data class SponsorsResponse(
    val status: String,
    val sponsor: List<SponsorResponse>,
)

@Serializable
public data class SponsorResponse(
    val sponsorName: String,
    val sponsorLogo: String,
    val plan: String,
    val link: String,
)
