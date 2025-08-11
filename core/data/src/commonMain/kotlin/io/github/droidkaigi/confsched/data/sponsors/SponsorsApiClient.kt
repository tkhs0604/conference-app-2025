package io.github.droidkaigi.confsched.data.sponsors
import io.github.droidkaigi.confsched.model.sponsors.Sponsor
import kotlinx.collections.immutable.PersistentList

public interface SponsorsApiClient {
    public suspend fun sponsors(): PersistentList<Sponsor>
}
