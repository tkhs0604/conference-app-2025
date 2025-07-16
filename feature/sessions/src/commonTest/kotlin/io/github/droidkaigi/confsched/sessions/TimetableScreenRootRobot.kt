package io.github.droidkaigi.confsched.sessions

import androidx.compose.ui.test.ComposeUiTest
import io.github.droidkaigi.confsched.testing.TestAppGraph
import io.github.droidkaigi.confsched.testing.TestCompositionLocalProvider
import io.github.droidkaigi.confsched.testing.createTestAppGraph

class TimetableScreenRootRobot private constructor(private val composeUiTest: ComposeUiTest) {
    companion object {
        context(composeUiTest: ComposeUiTest)
        operator fun invoke(): TimetableScreenRootRobot {
            return TimetableScreenRootRobot(composeUiTest)
        }
    }

    private val testAppGraph: TestAppGraph = createTestAppGraph()

    fun setupContent() {
        composeUiTest.setContent {
            with(testAppGraph.createTimetableScreenContext()) {
                TestCompositionLocalProvider {
                    TimetableScreenRoot(
                        onSearchClick = {},
                        onTimetableItemClick = {},
                    )
                }
            }
        }
    }

    fun clickFirstSession() {
        println("clickFirstSession")
    }
}
