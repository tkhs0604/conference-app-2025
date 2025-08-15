package io.github.droidkaigi.confsched.data.sponsors

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.GET
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.data.core.NetworkExceptionHandler
import io.github.droidkaigi.confsched.data.sponsors.response.SponsorResponse
import io.github.droidkaigi.confsched.data.sponsors.response.SponsorsResponse
import io.github.droidkaigi.confsched.model.sponsors.Sponsor
import io.github.droidkaigi.confsched.model.sponsors.SponsorPlan
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

internal interface SponsorsApi {
    @GET("events/droidkaigi2025/sponsor")
    suspend fun getSponsors(): SponsorsResponse
}

@ContributesBinding(DataScope::class)
@Inject
public class DefaultSponsorsApiClient(
    private val networkExceptionHandler: NetworkExceptionHandler,
    ktorfit: Ktorfit,
) : SponsorsApiClient {
    private val api = ktorfit.createSponsorsApi()

    override suspend fun sponsors(): PersistentList<Sponsor> {
        return networkExceptionHandler {
            api.getSponsors().toSponsorList()
        }
    }
}

private fun SponsorsResponse.toSponsorList(): PersistentList<Sponsor> {
    return sponsors.mapIndexed { index, sponsor -> 
        sponsor.toSponsor(index)
    }.toPersistentList()
}

private fun SponsorResponse.toSponsor(index: Int): Sponsor {
    return Sponsor(
        id = "sponsor_$index",
        name = sponsorName,
        logo = sponsorLogo,
        plan = SponsorPlan.ofOrNull(plan) ?: SponsorPlan.SUPPORTER,
        link = link,
    )
}
