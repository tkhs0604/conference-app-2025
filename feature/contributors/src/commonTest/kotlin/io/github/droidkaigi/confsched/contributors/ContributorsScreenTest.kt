package io.github.droidkaigi.confsched.contributors

import io.github.droidkaigi.confsched.testing.annotations.RunWith
import io.github.droidkaigi.confsched.testing.annotations.UiTestRunner
import io.github.droidkaigi.confsched.testing.di.createContributorsScreenTestGraph

@RunWith(UiTestRunner::class)
class ContributorsScreenTest {

    val testAppGraph = createContributorsScreenTestGraph()
}
