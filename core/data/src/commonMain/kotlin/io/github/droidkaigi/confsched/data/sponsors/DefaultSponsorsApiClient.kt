package io.github.droidkaigi.confsched.data.sponsors

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.GET
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.data.sponsors.response.SponsorResponse
import io.github.droidkaigi.confsched.data.sponsors.response.SponsorsResponse
import io.github.droidkaigi.confsched.model.sponsors.Sponsor
import io.github.droidkaigi.confsched.model.sponsors.SponsorPlan
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

public interface SponsorsApi {
    @GET("sponsors")
    public suspend fun getSponsors(): SponsorsResponse
}

@ContributesBinding(DataScope::class)
@Inject
public class DefaultSponsorsApiClient(
    ktorfit: Ktorfit,
) : SponsorsApiClient {
    private val api = ktorfit.create<SponsorsApi>()

    override suspend fun sponsors(): PersistentList<Sponsor> {
        val response = api.getSponsors()
        return response.sponsors.map { it.toSponsor() }.toPersistentList()
    }
}

private fun SponsorResponse.toSponsor(): Sponsor {
    return Sponsor(
        id = id,
        name = name,
        logo = logo,
        plan = when (plan.uppercase()) {
            "PLATINUM" -> SponsorPlan.PLATINUM
            "GOLD" -> SponsorPlan.GOLD
            "SILVER" -> SponsorPlan.SILVER
            "BRONZE" -> SponsorPlan.BRONZE
            "SUPPORTER" -> SponsorPlan.SUPPORTER
            else -> SponsorPlan.SUPPORTER
        },
        link = link,
    )
}