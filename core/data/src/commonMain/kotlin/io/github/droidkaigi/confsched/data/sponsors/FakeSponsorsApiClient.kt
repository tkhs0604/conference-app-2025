package io.github.droidkaigi.confsched.data.sponsors

import io.github.droidkaigi.confsched.model.sponsors.Sponsor
import io.github.droidkaigi.confsched.model.sponsors.SponsorPlan
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

public class FakeSponsorsApiClient : SponsorsApiClient {
    override suspend fun sponsors(): PersistentList<Sponsor> {
        return persistentListOf(
            Sponsor(
                id = "1",
                name = "Platinum Sponsor",
                logo = "https://example.com/logo1.png",
                plan = SponsorPlan.PLATINUM,
                link = "https://example.com",
            ),
            Sponsor(
                id = "2",
                name = "Gold Sponsor",
                logo = "https://example.com/logo2.png",
                plan = SponsorPlan.GOLD,
                link = "https://example.com",
            ),
            Sponsor(
                id = "3",
                name = "Silver Sponsor",
                logo = "https://example.com/logo3.png",
                plan = SponsorPlan.SILVER,
                link = "https://example.com",
            ),
        )
    }
}