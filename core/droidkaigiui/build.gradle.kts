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

            api(libs.coil)
            api(libs.coilNetwork)
        }
    }
}
