package io.github.droidkaigi.confsched.testing.di

import dev.zacsweers.metro.Binds
import dev.zacsweers.metro.Provider
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.data.contributors.ContributorsApiClient
import io.github.droidkaigi.confsched.data.contributors.FakeContributorsApiClient
import io.github.droidkaigi.confsched.data.sessions.FakeSessionsApiClient
import io.github.droidkaigi.confsched.data.sessions.SessionsApiClient
import io.github.droidkaigi.confsched.sessions.TimetableScreenContext
import io.github.droidkaigi.confsched.testing.robot.sessions.TimetableScreenRobot

interface TestAppGraph : TimetableScreenContext.Factory {
    val timetableScreenRobotProvider: Provider<TimetableScreenRobot>

    @Binds
    val FakeSessionsApiClient.binds: SessionsApiClient

    @Binds
    val FakeContributorsApiClient.binds: ContributorsApiClient

    @Provides
    fun provideTimetableScreenContext(): TimetableScreenContext {
        return createTimetableScreenContext()
    }
}

expect fun createTestAppGraph(): TestAppGraph
