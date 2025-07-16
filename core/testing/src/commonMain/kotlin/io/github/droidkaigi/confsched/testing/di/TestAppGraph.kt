package io.github.droidkaigi.confsched.testing.di

import dev.zacsweers.metro.Binds
import io.github.droidkaigi.confsched.data.contributors.ContributorsApiClient
import io.github.droidkaigi.confsched.data.contributors.FakeContributorsApiClient
import io.github.droidkaigi.confsched.data.sessions.FakeSessionsApiClient
import io.github.droidkaigi.confsched.data.sessions.SessionsApiClient
import io.github.droidkaigi.confsched.sessions.TimetableScreenContext

interface TestAppGraph : TimetableScreenContext.Factory {
    @Binds
    val FakeSessionsApiClient.binds: SessionsApiClient

    @Binds
    val FakeContributorsApiClient.binds: ContributorsApiClient
}

expect fun createTestAppGraph(): TestAppGraph
