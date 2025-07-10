plugins {
    id("droidkaigi.primitive.kmp")
    id("droidkaigi.primitive.metro")
    id("droidkaigi.primitive.kmp.compose")
    id("org.jetbrains.kotlin.plugin.serialization")
}

android.namespace = "io.github.droidkaigi.confsched"

dependencies {
    commonMainImplementation(projects.core.data)
    commonMainImplementation(projects.core.common)
    commonMainImplementation(projects.core.droidkaigiui)
    commonMainImplementation(projects.core.model)
    commonMainImplementation(projects.core.designsystem)

    commonMainImplementation(projects.feature.sessions)
    commonMainImplementation(projects.feature.about)
    commonMainImplementation(projects.feature.main)
    commonMainImplementation(projects.feature.sponsors)
    commonMainImplementation(projects.feature.settings)
    commonMainImplementation(projects.feature.staff)
    commonMainImplementation(projects.feature.contributors)
    commonMainImplementation(projects.feature.favorites)

    commonMainImplementation(libs.navigation3Ui)
    commonMainImplementation(libs.navigation3Runtime)

    commonMainImplementation(libs.kotlinxSerializationJson)
    commonMainImplementation(libs.rin)

    commonMainImplementation(libs.soilQueryCompose)
    // need this for compile success
    commonMainImplementation(libs.androidxDatastorePreferencesCore)

    debugImplementation(compose.uiTooling)
    androidMainImplementation(libs.androidxActivityCompose)
    androidMainImplementation(libs.navigation3Adaptive)

}
