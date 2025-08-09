package io.github.droidkaigi.confsched.model.sponsors

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class Sponsor(
    val name: String,
    val logo: String,
    val plan: SponsorPlan,
    val link: String,
) {
    companion object
}

enum class SponsorPlan {
    PLATINUM,
    GOLD,
    SUPPORTER;

    companion object {
        fun ofOrNull(plan: String): SponsorPlan? {
            return entries.firstOrNull { it.name == plan }
        }
    }
}

fun Sponsor.Companion.fakes(): PersistentList<Sponsor> {
    return persistentListOf(
        Sponsor(
            name = "DroidKaigi PLATINUM",
            logo = "https://placehold.jp/150x150.png",
            plan = SponsorPlan.PLATINUM,
            link = "https://developer.android.com/",
        ),
        Sponsor(
            name = "DroidKaigi GOLD",
            logo = "https://placehold.jp/150x150.png",
            plan = SponsorPlan.GOLD,
            link = "https://developer.android.com/",
        ),
        Sponsor(
            name = "DroidKaigi Supporter",
            logo = "https://placehold.jp/150x150.png",
            plan = SponsorPlan.SUPPORTER,
            link = "https://developer.android.com/",
        )
    )
}
