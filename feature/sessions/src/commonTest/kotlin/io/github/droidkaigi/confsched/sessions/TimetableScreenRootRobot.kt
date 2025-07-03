package io.github.droidkaigi.confsched.sessions

import androidx.compose.ui.test.ComposeUiTest
import dev.zacsweers.metro.createGraph
import io.github.droidkaigi.confsched.testing.TestAppGraph
import io.github.droidkaigi.confsched.testing.TestCompositionLocalProvider

class TimetableScreenRootRobot private constructor(private val composeUiTest: ComposeUiTest) {
    companion object {
        context(composeUiTest: ComposeUiTest)
        operator fun invoke(): TimetableScreenRootRobot {
            return TimetableScreenRootRobot(composeUiTest)
        }
    }

    private val testAppGraph: TestAppGraph = createGraph()

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
