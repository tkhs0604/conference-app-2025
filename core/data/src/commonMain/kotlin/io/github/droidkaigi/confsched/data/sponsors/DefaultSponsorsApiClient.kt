package io.github.droidkaigi.confsched.data.sponsors

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.GET
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.data.core.NetworkExceptionHandler
import io.github.droidkaigi.confsched.data.sponsors.response.SponsorsResponse
import io.github.droidkaigi.confsched.model.sponsors.Plan
import io.github.droidkaigi.confsched.model.sponsors.Sponsor
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

internal interface SponsorApi {
    @GET("/events/droidkaigi2025/sponsors")
    suspend fun getSponsors(): SponsorsResponse
}

@ContributesBinding(DataScope::class, binding<SponsorsApiClient>())
@Inject
public class DefaultSponsorsApiClient(
    private val networkService: NetworkExceptionHandler,
    ktorfit: Ktorfit,
) : SponsorsApiClient {
    private val sponsorApi: SponsorApi = ktorfit.createSponsorApi()

    public override suspend fun sponsors(): PersistentList<Sponsor> {
        return networkService {
            sponsorApi.getSponsors()
        }.toSponsorList()
    }
}

private fun SponsorsResponse.toSponsorList(): PersistentList<Sponsor> {
    return sponsors.map {
        Sponsor(
            name = it.name,
            logo = it.logo,
            plan = Plan.valueOf(it.plan),
            link = it.link,
        )
    }.toPersistentList()
}
