import util.androidJvmMain

plugins {
    id("droidkaigi.primitive.kmp")
    id("droidkaigi.primitive.metro")
    id("droidkaigi.primitive.kmp.compose")
    id("droidkaigi.primitive.kmp.compose.resources")
    id("droidkaigi.primitive.buildkonfig")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android.namespace = "io.github.droidkaigi.confsched"

compose.resources.nameOfResClass = "AppSharedRes"

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.data)
            implementation(projects.core.common)
            implementation(projects.core.droidkaigiui)
            implementation(projects.core.model)
            implementation(projects.core.designsystem)
            implementation(projects.feature.sessions)
            implementation(projects.feature.about)
            implementation(projects.feature.sponsors)
            implementation(projects.feature.settings)
            implementation(projects.feature.staff)
            implementation(projects.feature.contributors)
            implementation(projects.feature.favorites)

            implementation(libs.kotlinxSerializationJson)
            implementation(libs.rin)
            implementation(libs.soilQueryCompose)
            implementation(libs.androidxDatastorePreferencesCore)
            implementation(libs.haze)
        }

        androidMain.dependencies {
            implementation(libs.lifecycleViewmodelNavigation3)
            implementation(libs.androidxActivityCompose)
            implementation(libs.navigation3Adaptive)
            implementation(libs.androidxBrowser)
        }

        androidJvmMain.dependencies {
            implementation(libs.navigation3Ui.get().toString()) {
                /**
                 * Exclude androidx.compose.ui to avoid runtime crash on JVM:
                 * ```
                 * kotlin.NotImplementedError: Implemented only in JetBrains fork.
                 * Please use `org.jetbrains.compose.ui:ui-util` package instead.
                 * ```
                 *
                 * navigation3Ui includes non-multiplatform code and is not officially supported on JVM.
                 * This exclusion is a workaround to prevent the crash.
                 */
                exclude(group = "androidx.compose.ui")
            }
            implementation(libs.navigation3Runtime)
        }
    }
}

dependencies {
    debugImplementation(compose.uiTooling)
}
