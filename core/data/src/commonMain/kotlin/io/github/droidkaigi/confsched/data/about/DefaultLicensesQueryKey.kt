package io.github.droidkaigi.confsched.data.about

import dev.zacsweers.metro.ContributesBinding
import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.binding
import io.github.droidkaigi.confsched.common.scope.LicensesScope
import io.github.droidkaigi.confsched.model.about.LicensesQueryKey
import org.jetbrains.compose.resources.InternalResourceApi
import soil.query.QueryId
import soil.query.buildQueryKey

@OptIn(InternalResourceApi::class)
@ContributesBinding(LicensesScope::class, binding<LicensesQueryKey>())
@Inject
public class DefaultLicensesQueryKey(
    private val licensesJsonReader: LicensesJsonReader,
) : LicensesQueryKey by buildQueryKey(
    id = QueryId("licenses"),
    fetch = {
        licensesJsonReader.readLicensesJson()
    },
)

public interface LicensesJsonReader {
    public suspend fun readLicensesJson(): String
}
