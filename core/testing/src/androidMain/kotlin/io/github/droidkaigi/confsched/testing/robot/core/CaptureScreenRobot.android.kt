package io.github.droidkaigi.confsched.testing.robot.core

import androidx.compose.ui.test.SemanticsNodeInteraction
import com.github.takahirom.roborazzi.captureRoboImage

actual fun SemanticsNodeInteraction.captureNodeWithDescription(description: String) {
    this.captureRoboImage("$description.png")
}
