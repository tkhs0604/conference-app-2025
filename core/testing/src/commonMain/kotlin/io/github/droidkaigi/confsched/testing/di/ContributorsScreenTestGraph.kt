package io.github.droidkaigi.confsched.testing.di

import dev.zacsweers.metro.Provider
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.contributors.ContributorsScreenContext

interface ContributorsScreenTestGraph : ContributorsScreenContext.Factory {
    val contributorsScreenRobotProvider: Provider<ContributorsScreenRobot>

    @Provides
    fun provideContributorsScreenContext(): ContributorsScreenContext {
        return createContributorsScreenContext()
    }
}

fun createContributorsScreenTestGraph(): ContributorsScreenTestGraph = createTestAppGraph()
