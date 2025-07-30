plugins {
    id("droidkaigi.primitive.kmp")
    id("droidkaigi.primitive.kmp.compose")
    id("droidkaigi.primitive.kmp.compose.resources")
    id("droidkaigi.primitive.detekt")
}

kotlin {
    sourceSets {
        commonMain.dependencies {
            implementation(projects.core.common)
            implementation(projects.core.designsystem)
            implementation(projects.core.model)
            implementation(libs.soilQueryCompose)
            implementation(libs.soilReacty)
            implementation(libs.navigation3Ui)
            implementation(libs.navigation3Runtime)

            api(libs.coil)
            api(libs.coilNetwork)
        }

        androidMain.dependencies {
            implementation(libs.lifecycleViewmodelNavigation3)
            implementation(libs.navigation3Adaptive)
        }
    }
}
