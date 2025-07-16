package io.github.droidkaigi.confsched.sessions

import androidx.compose.ui.test.runComposeUiTest
import io.github.droidkaigi.confsched.testing.annotations.ComposeTest
import io.github.droidkaigi.confsched.testing.annotations.RunWith
import io.github.droidkaigi.confsched.testing.annotations.UiTestRunner

@RunWith(UiTestRunner::class)
class TimetableScreenTest {
    @ComposeTest
    fun test() = runComposeUiTest {
        val robot = TimetableScreenRobot()
        robot.setupContent()
        robot.clickFirstSession()
    }
}
