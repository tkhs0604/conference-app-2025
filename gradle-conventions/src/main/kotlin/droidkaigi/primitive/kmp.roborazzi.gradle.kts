package droidkaigi.primitive

import com.android.build.gradle.BaseExtension
import io.github.takahirom.roborazzi.RoborazziExtension
import util.getDefaultPackageName
import util.kotlin
import util.library
import util.libs

plugins {
    id("io.github.takahirom.roborazzi")
}

configure<RoborazziExtension> {
    generateComposePreviewRobolectricTests {
        enable = true
        packages = listOf(getDefaultPackageName(project.name))
        includePrivatePreviews = true
        testerQualifiedClassName = "io.github.droidkaigi.confsched.testing.previewtester.DroidKaigiKmpPreviewTester"
    }
}

configure<BaseExtension> {
    testOptions.unitTests.all {
        it.systemProperties["robolectric.graphicsMode"] = "NATIVE"
        it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
    }
}

kotlin {
    sourceSets {
        androidUnitTest {
            dependencies {
                implementation(libs.library("roborazziPreviewScannerSupport"))
                implementation(libs.library("composablePreviewScannerAndroid"))
                implementation(libs.library("junit"))
                implementation(libs.library("robolectric"))
            }
        }
    }
}
