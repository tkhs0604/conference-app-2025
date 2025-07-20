package io.github.droidkaigi.confsched.testing.robot.core

import org.robolectric.shadows.ShadowLooper

actual fun runUiThreadTasksIncludingDelayedTasksOnRobolectricShadowLooper() {
    ShadowLooper.runUiThreadTasksIncludingDelayedTasks()
}
