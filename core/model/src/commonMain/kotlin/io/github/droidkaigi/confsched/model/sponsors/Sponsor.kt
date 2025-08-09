package io.github.droidkaigi.confsched.model.sponsors

data class Sponsor(
    val id: String,
    val name: String,
    val logo: String,
    val plan: SponsorPlan,
    val link: String,
)

enum class SponsorPlan {
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE,
    SUPPORTER,
}