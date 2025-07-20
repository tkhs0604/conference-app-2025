package io.github.droidkaigi.confsched.testing.robot.core

import androidx.compose.ui.test.SemanticsNodeInteraction

actual fun SemanticsNodeInteraction.captureNodeWithDescription(description: String) {
    /**
     * No-op for JVM, as we cannot make it work with Roborazzi for now.
     *
     * If we add "io.github.takahirom.roborazzi:roborazzi-compose-desktop" to jvm dependencies,
     * compose tests will fail with the following error:
     *
     * ```
     * 'void androidx.compose.ui.test.AbstractMainTestClock.<init>(kotlinx.coroutines.test.TestCoroutineScheduler, long, kotlin.jvm.functions.Function1)'
     * java.lang.NoSuchMethodError: 'void androidx.compose.ui.test.AbstractMainTestClock.<init>(kotlinx.coroutines.test.TestCoroutineScheduler, long, kotlin.jvm.functions.Function1)'
     *     at androidx.compose.ui.test.MainTestClockImpl.<init>(MainTestClockImpl.skikoMain.kt:24)
     *     at androidx.compose.ui.test.SkikoComposeUiTest.<init>(ComposeUiTest.skiko.kt:203)
     *     at androidx.compose.ui.test.SkikoComposeUiTest.<init>(ComposeUiTest.skiko.kt)
     *     at androidx.compose.ui.test.SkikoComposeUiTest.<init>(ComposeUiTest.skiko.kt:153)
     *     at androidx.compose.ui.test.SkikoComposeUiTest.<init>(ComposeUiTest.skiko.kt:191)
     *     at androidx.compose.ui.test.SkikoComposeUiTest.<init>(ComposeUiTest.skiko.kt)
     *     ...
     * ```
     */
}
