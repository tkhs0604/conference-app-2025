package io.github.droidkaigi.confsched

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.model.sponsors.Sponsor
import io.github.droidkaigi.confsched.model.sponsors.SponsorsQueryKey
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.timeout
import soil.query.SwrClientPlus
import soil.query.annotation.ExperimentalSoilQueryApi
import soil.query.compose.QuerySuccessObject
import soil.query.compose.rememberQuery
import kotlin.time.Duration.Companion.milliseconds

@Inject
class SponsorsRepository(
    private val swrClient: SwrClientPlus,
    private val sponsorsQueryKey: SponsorsQueryKey,
) {
    @OptIn(ExperimentalSoilQueryApi::class, FlowPreview::class)
    fun sponsorsFlow(): Flow<PersistentList<Sponsor>> = moleculeFlow(RecompositionMode.Immediate) {
        val sponsorsQuery = rememberQuery(
            key = sponsorsQueryKey,
            client = swrClient,
        )

        if (sponsorsQuery is QuerySuccessObject<PersistentList<Sponsor>>) {
            sponsorsQuery.data
        } else {
            null
        }
    }
        .filterNotNull()
        .timeout(5000.milliseconds)
        // Errors thrown inside flow can't be caught on iOS side, so we catch it here.
        .catch { emit(persistentListOf()) }
}