package io.github.droidkaigi.confsched.testing.robot.core

import androidx.compose.ui.test.ComposeUiTest
import dev.zacsweers.metro.Inject
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestDispatcher

interface WaitRobot {
    context(composeUiTest: ComposeUiTest)
    fun waitUntilIdle()

    context(composeUiTest: ComposeUiTest)
    fun waitFor5Seconds()
}

expect fun runUiThreadTasksIncludingDelayedTasksOnRobolectricShadowLooper()

@Inject
class DefaultWaitRobot(
    private val testDispatcher: TestDispatcher,
) : WaitRobot {
    context(composeUiTest: ComposeUiTest)
    override fun waitUntilIdle() {
        composeUiTest.waitForIdle()
        testDispatcher.scheduler.advanceUntilIdle()
        runUiThreadTasksIncludingDelayedTasksOnRobolectricShadowLooper()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    context(composeUiTest: ComposeUiTest)
    override fun waitFor5Seconds() {
        repeat(5) {
            composeUiTest.mainClock.advanceTimeBy(1000)
            testDispatcher.scheduler.advanceTimeBy(1000)
            runUiThreadTasksIncludingDelayedTasksOnRobolectricShadowLooper()
        }
    }
}
