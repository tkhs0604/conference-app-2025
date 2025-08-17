package io.github.droidkaigi.confsched.model.sponsors

import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

data class Sponsor(
    val id: String,
    val name: String,
    val logo: String,
    val plan: SponsorPlan,
    val link: String,
) {
    companion object
}

data class SponsorList(
    val platinumSponsors: List<Sponsor>,
    val goldSponsors: List<Sponsor>,
    val supporters: List<Sponsor>
)

enum class SponsorPlan {
    PLATINUM,
    GOLD,
    SILVER,
    BRONZE,
    SUPPORTER,
    ;

    companion object {
        fun ofOrNull(plan: String): SponsorPlan? {
            return entries.firstOrNull { it.name == plan }
        }
    }
}

fun Sponsor.Companion.fakes(): PersistentList<Sponsor> {
    return persistentListOf(
        Sponsor(
            id = "1",
            name = "DroidKaigi PLATINUM",
            logo = "https://placehold.jp/150x150.png",
            plan = SponsorPlan.PLATINUM,
            link = "https://developer.android.com/",
        ),
        Sponsor(
            id = "2",
            name = "DroidKaigi GOLD",
            logo = "https://placehold.jp/150x150.png",
            plan = SponsorPlan.GOLD,
            link = "https://developer.android.com/",
        ),
        Sponsor(
            id = "3",
            name = "DroidKaigi SILVER",
            logo = "https://placehold.jp/150x150.png",
            plan = SponsorPlan.SILVER,
            link = "https://developer.android.com/",
        ),
        Sponsor(
            id = "4",
            name = "DroidKaigi BRONZE",
            logo = "https://placehold.jp/150x150.png",
            plan = SponsorPlan.BRONZE,
            link = "https://developer.android.com/",
        ),
        Sponsor(
            id = "5",
            name = "DroidKaigi Supporter",
            logo = "https://placehold.jp/150x150.png",
            plan = SponsorPlan.SUPPORTER,
            link = "https://developer.android.com/",
        ),
    )
}
