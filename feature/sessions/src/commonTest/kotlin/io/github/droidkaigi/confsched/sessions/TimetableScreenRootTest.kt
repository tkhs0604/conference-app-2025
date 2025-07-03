package io.github.droidkaigi.confsched.sessions

import androidx.compose.ui.test.runComposeUiTest
import io.github.droidkaigi.confsched.sessions.TimetableScreenRootRobot
import kotlin.test.Test

class TimetableScreenRootTest {
    @Test
    fun test() = runComposeUiTest {
        val robot = TimetableScreenRootRobot()
        robot.setupContent()
        robot.clickFirstSession()
    }
}
