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
            // This explicit dependency is required to avoid unresolved references when referencing other module types.
            // This might be due to some modules using 'com.android.kotlin.multiplatform.library' instead of 'com.android.library'.
            // FIXME: This inconsistency could potentially cause issues, so it's probably better to use the same plugin in all modules.
            dependsOn(androidMain.get())
            dependencies {
                implementation(libs.library("roborazziPreviewScannerSupport"))
                implementation(libs.library("composablePreviewScannerAndroid"))
                implementation(libs.library("junit"))
                implementation(libs.library("robolectric"))
            }
        }
    }
}
