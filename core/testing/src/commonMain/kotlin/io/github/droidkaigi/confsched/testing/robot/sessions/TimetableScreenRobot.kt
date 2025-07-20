package io.github.droidkaigi.confsched.testing.robot.sessions

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.onNodeWithTag
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.droidkaigiui.DefaultSuspenseFallbackTestTag
import io.github.droidkaigi.confsched.model.core.DroidKaigi2025Day
import io.github.droidkaigi.confsched.sessions.TimetableScreenContext
import io.github.droidkaigi.confsched.sessions.TimetableScreenRoot
import io.github.droidkaigi.confsched.testing.compose.TestDefaultsProvider
import io.github.droidkaigi.confsched.testing.robot.core.CaptureScreenRobot
import io.github.droidkaigi.confsched.testing.robot.core.DefaultCaptureScreenRobot
import io.github.droidkaigi.confsched.testing.robot.core.DefaultWaitRobot
import io.github.droidkaigi.confsched.testing.robot.core.WaitRobot
import kotlinx.coroutines.test.TestDispatcher

@Inject
class TimetableScreenRobot(
    private val screenContext: TimetableScreenContext,
    private val testDispatcher: TestDispatcher,
    timetableServerRobot: DefaultTimetableServerRobot,
    captureScreenRobot: DefaultCaptureScreenRobot,
    waitRobot: DefaultWaitRobot,
) :
    TimetableServerRobot by timetableServerRobot,
    CaptureScreenRobot by captureScreenRobot,
    WaitRobot by waitRobot {

    context(composeUiTest: ComposeUiTest)
    fun setupTimetableScreenContent() {
        composeUiTest.setContent {
            with(screenContext) {
                TestDefaultsProvider(testDispatcher) {
                    TimetableScreenRoot(
                        onSearchClick = {},
                        onTimetableItemClick = {},
                    )
                }
            }
        }
    }

    context(composeUiTest: ComposeUiTest)
    fun checkLoadingIndicatorDisplayed() {
        composeUiTest.onNodeWithTag(DefaultSuspenseFallbackTestTag).assertExists()
    }

    context(composeUiTest: ComposeUiTest)
    fun checkLoadingIndicatorNotDisplayed() {
        composeUiTest.onNodeWithTag(DefaultSuspenseFallbackTestTag).assertDoesNotExist()
    }

    context(composeUiTest: ComposeUiTest)
    fun clickFirstSessionBookmark() {
        // TODO
    }

    context(composeUiTest: ComposeUiTest)
    fun checkTimetableListDisplayed() {
        // TODO
    }

    context(composeUiTest: ComposeUiTest)
    fun checkTimetableListItemsDisplayed() {
        // TODO
    }

    context(composeUiTest: ComposeUiTest)
    fun checkTimetableTabSelected(day: DroidKaigi2025Day) {
        // TODO
    }

    context(composeUiTest: ComposeUiTest)
    fun clickFirstSession() {
        // TODO
    }
}
