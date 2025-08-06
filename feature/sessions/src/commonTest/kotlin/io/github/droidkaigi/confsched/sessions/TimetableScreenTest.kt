package io.github.droidkaigi.confsched.sessions

import androidx.compose.ui.test.runComposeUiTest
import io.github.droidkaigi.confsched.model.core.DroidKaigi2025Day
import io.github.droidkaigi.confsched.testing.annotations.ComposeTest
import io.github.droidkaigi.confsched.testing.annotations.RunWith
import io.github.droidkaigi.confsched.testing.annotations.UiTestRunner
import io.github.droidkaigi.confsched.testing.behavior.describeBehaviors
import io.github.droidkaigi.confsched.testing.behavior.execute
import io.github.droidkaigi.confsched.testing.di.createTimetableScreenTestGraph
import io.github.droidkaigi.confsched.testing.robot.sessions.TimetableScreenRobot
import io.github.droidkaigi.confsched.testing.robot.sessions.TimetableServerRobot.ServerStatus

@RunWith(UiTestRunner::class)
class TimetableScreenTest {
    val testAppGraph = createTimetableScreenTestGraph()

    @ComposeTest
    fun runTest() {
        describedBehaviors.forEach { behavior ->
            val robot = testAppGraph.timetableScreenRobotProvider()
            runComposeUiTest {
                behavior.execute(robot)
            }
        }
    }

    val describedBehaviors = describeBehaviors<TimetableScreenRobot>("TimetableScreen") {
        describe("when server is operational") {
            doIt {
                setupTimetableServer(ServerStatus.Operational)
                setupTimetableScreenContent()
            }
            itShould("show loading indicator") {
                captureScreenWithChecks {
                    checkLoadingIndicatorDisplayed()
                }
            }
            describe("after loading") {
                doIt {
                    waitFor5Seconds()
                }
                itShould("show timetable items") {
                    captureScreenWithChecks {
                        checkLoadingIndicatorNotDisplayed()
                        checkTimetableListDisplayed()
                        checkTimetableListItemsDisplayed()
                        checkTimetableTabSelected(DroidKaigi2025Day.ConferenceDay1)
                    }
                }
                describe("click first session bookmark") {
                    doIt {
                        clickFirstSessionBookmark()
                    }
                }
            }
        }
    }
}
