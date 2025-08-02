plugins {
    id("droidkaigi.primitive.kmp")
    id("droidkaigi.primitive.metro")
    id("droidkaigi.primitive.kmp.compose")
    id("droidkaigi.primitive.kmp.compose.resources")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android.namespace = "io.github.droidkaigi.confsched"

compose.resources.nameOfResClass = "AppSharedRes"

dependencies {
    commonMainImplementation(projects.core.data)
    commonMainImplementation(projects.core.common)
    commonMainImplementation(projects.core.droidkaigiui)
    commonMainImplementation(projects.core.model)
    commonMainImplementation(projects.core.designsystem)

    commonMainImplementation(projects.feature.sessions)
    commonMainImplementation(projects.feature.about)
    commonMainImplementation(projects.feature.sponsors)
    commonMainImplementation(projects.feature.settings)
    commonMainImplementation(projects.feature.staff)
    commonMainImplementation(projects.feature.contributors)
    commonMainImplementation(projects.feature.favorites)

    commonMainImplementation(libs.navigation3Ui) {
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
    commonMainImplementation(libs.navigation3Runtime)
    androidMainImplementation(libs.lifecycleViewmodelNavigation3)

    commonMainImplementation(libs.kotlinxSerializationJson)
    commonMainImplementation(libs.rin)

    commonMainImplementation(libs.soilQueryCompose)
    // need this for compile success
    commonMainImplementation(libs.androidxDatastorePreferencesCore)
    commonMainImplementation(libs.haze)

    debugImplementation(compose.uiTooling)
    androidMainImplementation(libs.androidxActivityCompose)
    androidMainImplementation(libs.navigation3Adaptive)
    androidMainImplementation(libs.androidxBrowser)
}
