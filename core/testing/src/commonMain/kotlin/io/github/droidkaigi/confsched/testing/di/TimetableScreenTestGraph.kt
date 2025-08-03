package io.github.droidkaigi.confsched.testing.di

import dev.zacsweers.metro.Provider
import dev.zacsweers.metro.Provides
import io.github.droidkaigi.confsched.sessions.TimetableScreenContext
import io.github.droidkaigi.confsched.testing.robot.sessions.TimetableScreenRobot

interface TimetableScreenTestGraph : TimetableScreenContext.Factory {
    val timetableScreenRobotProvider: Provider<TimetableScreenRobot>

    @Provides
    fun provideTimetableScreenContext(): TimetableScreenContext {
        return createTimetableScreenContext()
    }
}

fun createTimetableScreenTestGraph(): TimetableScreenTestGraph = createTestAppGraph()
