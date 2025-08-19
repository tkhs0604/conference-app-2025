package io.github.droidkaigi.confsched.testing.robot.contributors

import androidx.compose.ui.test.ComposeUiTest
import androidx.compose.ui.test.assertContentDescriptionEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.performScrollToIndex
import dev.zacsweers.metro.Inject
import io.github.droidkaigi.confsched.contributors.ContributorsItemTestTagPrefix
import io.github.droidkaigi.confsched.contributors.ContributorsScreenContext
import io.github.droidkaigi.confsched.contributors.ContributorsScreenRoot
import io.github.droidkaigi.confsched.contributors.ContributorsTestTag
import io.github.droidkaigi.confsched.contributors.ContributorsTotalCountTestTag
import io.github.droidkaigi.confsched.contributors.component.ContributorsItemImageTestTagPrefix
import io.github.droidkaigi.confsched.contributors.component.ContributorsUserNameTextTestTagPrefix
import io.github.droidkaigi.confsched.model.contributors.Contributor
import io.github.droidkaigi.confsched.model.contributors.fakes
import io.github.droidkaigi.confsched.testing.compose.TestDefaultsProvider
import io.github.droidkaigi.confsched.testing.robot.core.CaptureScreenRobot
import io.github.droidkaigi.confsched.testing.robot.core.DefaultCaptureScreenRobot
import io.github.droidkaigi.confsched.testing.robot.core.DefaultWaitRobot
import io.github.droidkaigi.confsched.testing.robot.core.WaitRobot
import io.github.droidkaigi.confsched.testing.utils.assertCountAtLeast
import io.github.droidkaigi.confsched.testing.utils.hasTestTag
import kotlinx.coroutines.test.TestDispatcher

@Inject
class ContributorsScreenRobot(
    private val screenContext: ContributorsScreenContext,
    private val testDispatcher: TestDispatcher,
    contributorsServerRobot: DefaultContributorsServerRobot,
    captureScreenRobot: DefaultCaptureScreenRobot,
    waitRobot: DefaultWaitRobot,
) : ContributorsServerRobot by contributorsServerRobot,
    CaptureScreenRobot by captureScreenRobot,
    WaitRobot by waitRobot {

    context(composeUiTest: ComposeUiTest)
    fun setupContributorsScreenContent() {
        composeUiTest.setContent {
            with(screenContext) {
                TestDefaultsProvider(testDispatcher) {
                    ContributorsScreenRoot(
                        onBackClick = {},
                        onContributorClick = { },
                    )
                }
            }
        }
    }

    context(composeUiTest: ComposeUiTest)
    fun scrollToIndex10() {
        composeUiTest
            .onNode(hasTestTag(ContributorsTestTag))
            .performScrollToIndex(10)
    }

    context(composeUiTest: ComposeUiTest)
    fun checkShowFirstAndSecondContributors() {
        checkRangeContributorItemsDisplayed(
            fromTo = 0..2,
        )
    }

    context(composeUiTest: ComposeUiTest)
    private fun checkRangeContributorItemsDisplayed(
        fromTo: IntRange,
    ) {
        val contributorsList = Contributor.fakes().subList(fromTo.first, fromTo.last)
        contributorsList.forEach { contributor ->
            composeUiTest
                .onNode(hasTestTag(ContributorsItemTestTagPrefix.plus(contributor.id)))
                .assertExists()
                .assertIsDisplayed()

            composeUiTest
                .onNode(
                    matcher = hasTestTag(ContributorsItemImageTestTagPrefix.plus(contributor.username)),
                    useUnmergedTree = true,
                )
                .assertExists()
                .assertIsDisplayed()
                .assertContentDescriptionEquals(contributor.username)

            composeUiTest
                .onNode(
                    matcher = hasTestTag(ContributorsUserNameTextTestTagPrefix.plus(contributor.username)),
                    useUnmergedTree = true,
                )
                .assertExists()
                .assertIsDisplayed()
                .assertTextEquals(contributor.username)
        }
    }

    context(composeUiTest: ComposeUiTest)
    fun checkContributorItemsDisplayed() {
        // Check there are two contributors
        composeUiTest
            .onAllNodes(hasTestTag(ContributorsItemTestTagPrefix, substring = true))
            .assertCountAtLeast(2)
    }

    context(composeUiTest: ComposeUiTest)
    fun checkContributorTotalCountDisplayed() {
        // Check contributors total count is being displayed
        composeUiTest
            .onNode(hasTestTag(ContributorsTotalCountTestTag))
            .assertExists()
            .isDisplayed()
    }

    context(composeUiTest: ComposeUiTest)
    fun checkDoesNotFirstContributorItemDisplayed() {
        val contributor = Contributor.fakes().first()
        composeUiTest
            .onNode(hasTestTag(ContributorsItemTestTagPrefix.plus(contributor.id)))
            .assertDoesNotExist()

        composeUiTest
            .onNode(
                matcher = hasTestTag(ContributorsItemImageTestTagPrefix.plus(contributor.username)),
                useUnmergedTree = true,
            )
            .assertDoesNotExist()

        composeUiTest
            .onNode(
                matcher = hasTestTag(ContributorsUserNameTextTestTagPrefix.plus(contributor.username)),
                useUnmergedTree = true,
            )
            .assertDoesNotExist()
    }
}
