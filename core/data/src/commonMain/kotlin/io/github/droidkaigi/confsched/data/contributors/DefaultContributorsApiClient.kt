package io.github.droidkaigi.confsched.data.contributors

import de.jensklingenberg.ktorfit.Ktorfit
import de.jensklingenberg.ktorfit.http.GET
import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.data.contributors.response.ContributorsResponse
import io.github.droidkaigi.confsched.data.core.NetworkExceptionHandler
import io.github.droidkaigi.confsched.model.contributors.Contributor
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.toPersistentList

internal interface ContributorApi {
    @GET("/events/droidkaigi2024/contributors")
    suspend fun getContributors(): ContributorsResponse
}

@ContributesBinding(DataScope::class, binding<ContributorsApiClient>())
@Inject
public class DefaultContributorsApiClient(
    private val networkService: NetworkExceptionHandler,
    ktorfit: Ktorfit,
) : ContributorsApiClient {
    private val contributorApi: ContributorApi = ktorfit.createContributorApi()

    public override suspend fun contributors(): PersistentList<Contributor> {
        return networkService {
            contributorApi.getContributors()
        }.toContributorList()
    }
}

private fun ContributorsResponse.toContributorList(): PersistentList<Contributor> {
    return contributors.map {
        Contributor(
            id = it.id,
            username = it.username,
            profileUrl = "https://github.com/${it.username}",
            iconUrl = it.iconUrl,
        )
    }.toPersistentList()
}
