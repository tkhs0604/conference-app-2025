package io.github.droidkaigi.confsched.data.contributors

import dev.zacsweers.metro.Inject
import dev.zacsweers.metro.SingleIn
import io.github.droidkaigi.confsched.data.DataScope
import io.github.droidkaigi.confsched.model.contributors.Contributor
import io.github.droidkaigi.confsched.model.contributors.fakes
import kotlinx.collections.immutable.PersistentList
import kotlinx.io.IOException

@SingleIn(DataScope::class)
@Inject
public class FakeContributorsApiClient : ContributorsApiClient {
    public sealed class Status : ContributorsApiClient {
        public data object Operational : Status() {
            override suspend fun contributors(): PersistentList<Contributor> {
                return Contributor.fakes()
            }
        }

        public data object Error : Status() {
            override suspend fun contributors(): PersistentList<Contributor> {
                throw IOException("Fake IO Exception")
            }
        }
    }

    private var status: Status = Status.Operational

    public fun setup(status: Status) {
        this.status = status
    }

    override suspend fun contributors(): PersistentList<Contributor> {
        return status.contributors()
    }
}
