import org.jetbrains.compose.ExperimentalComposeLibrary

plugins {
    id("droidkaigi.primitive.kmp")
    id("droidkaigi.primitive.metro")
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    compilerOptions.freeCompilerArgs.addAll(
        "-opt-in=androidx.compose.ui.test.ExperimentalTestApi",
        "-opt-in=soil.query.annotation.ExperimentalSoilQueryApi",
    )

    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.droidkaigiui)
            implementation(projects.core.model)
            implementation(projects.core.common)
            implementation(projects.core.droidkaigiui)
            implementation(projects.core.designsystem)

            implementation(projects.core.data)
            implementation(projects.feature.sessions)

            @OptIn(ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
            implementation(compose.runtime)
            implementation(compose.material3)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.soilQueryCompose)
            implementation(libs.soilQueryCore)
            implementation(libs.lifecycleViewmodelCompose)
            implementation(libs.lifecycleRuntimeCompose)
            implementation(libs.androidxDatastorePreferencesCore)
        }

        androidMain.dependencies {
            api(libs.robolectric)
            api(libs.androidxTestCore)
            api(libs.androidxActivityCompose)
            api(libs.rin)

            implementation(project.dependencies.platform(libs.composeBom))
            api(libs.androidxUiTestJunit4)
            implementation(libs.androidxUiTestManifest)
        }

        jvmMain.dependencies {
            implementation(libs.kotlinTestJunit)
        }
    }
}
