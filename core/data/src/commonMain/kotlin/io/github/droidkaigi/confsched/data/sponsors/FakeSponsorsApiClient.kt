package io.github.droidkaigi.confsched.data.sponsors

import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.data.contributors.ContributorsApiClient
import io.github.droidkaigi.confsched.model.contributors.Contributor
import io.github.droidkaigi.confsched.model.contributors.fakes
import io.github.droidkaigi.confsched.model.sponsors.Sponsor
import io.github.droidkaigi.confsched.model.sponsors.fakes
import kotlinx.collections.immutable.PersistentList
import kotlinx.io.IOException

@Inject
public class FakeSponsorsApiClient : SponsorsApiClient {
    public sealed class Status : SponsorsApiClient {
        public data object Operational : io.github.droidkaigi.confsched.data.sponsors.FakeSponsorsApiClient.Status() {
            override suspend fun sponsors(): PersistentList<Sponsor> {
                return Sponsor.fakes()
            }
        }

        public data object Error : io.github.droidkaigi.confsched.data.sponsors.FakeSponsorsApiClient.Status() {
            override suspend fun sponsors(): PersistentList<Sponsor> {
                throw IOException("Fake IO Exception")
            }
        }
    }

    private var status: io.github.droidkaigi.confsched.data.sponsors.FakeSponsorsApiClient.Status = Status.Operational

    public fun setup(status: io.github.droidkaigi.confsched.data.sponsors.FakeSponsorsApiClient.Status) {
        this.status = status
    }

    override suspend fun sponsors(): PersistentList<Sponsor> {
        return status.sponsors()
    }
}
