package io.github.droidkaigi.confsched.testing

import io.github.droidkaigi.confsched.sessions.TimetableScreenContext

interface TestAppGraph : TimetableScreenContext.Factory

expect fun createTestAppGraph(): TestAppGraph
