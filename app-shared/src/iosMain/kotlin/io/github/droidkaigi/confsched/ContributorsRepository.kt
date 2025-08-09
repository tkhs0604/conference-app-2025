package io.github.droidkaigi.confsched

import app.cash.molecule.RecompositionMode
import app.cash.molecule.moleculeFlow
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.model.contributors.Contributor
import io.github.droidkaigi.confsched.model.contributors.ContributorsQueryKey
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
class ContributorsRepository(
    private val swrClient: SwrClientPlus,
    private val contributorsQueryKey: ContributorsQueryKey,
) {
    @OptIn(ExperimentalSoilQueryApi::class, FlowPreview::class)
    fun contributorsFlow(): Flow<PersistentList<Contributor>> = moleculeFlow(RecompositionMode.Immediate) {
        val contributorsQuery = rememberQuery(
            key = contributorsQueryKey,
            client = swrClient,
        )

        if (contributorsQuery is QuerySuccessObject<PersistentList<Contributor>>) {
            contributorsQuery.data
        } else {
            null
        }
    }
        .filterNotNull()
        .timeout(5000.milliseconds)
        // Errors thrown inside flow can't be caught on iOS side, so we catch it here.
        .catch { emit(persistentListOf()) }
}