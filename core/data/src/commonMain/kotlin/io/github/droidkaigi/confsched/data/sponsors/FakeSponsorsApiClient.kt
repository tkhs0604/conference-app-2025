package io.github.droidkaigi.confsched.data.sponsors

import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.model.sponsors.Sponsor
import io.github.droidkaigi.confsched.model.sponsors.fakes
import kotlinx.collections.immutable.PersistentList
import kotlinx.io.IOException

@Inject
public class FakeSponsorsApiClient : SponsorsApiClient {
    public sealed class Status : SponsorsApiClient {
        public data object Operational : Status() {
            override suspend fun sponsors(): PersistentList<Sponsor> {
                return Sponsor.fakes()
            }
        }

        public data object Error : Status() {
            override suspend fun sponsors(): PersistentList<Sponsor> {
                throw IOException("Fake IO Exception")
            }
        }
    }

    private var status: Status = Status.Operational

    public fun setup(status: Status) {
        this.status = status
    }

    override suspend fun sponsors(): PersistentList<Sponsor> {
        return status.sponsors()
    }
}
