plugins {
    id("droidkaigi.primitive.kmp")
    id("droidkaigi.primitive.detekt")
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    iosArm64()

    sourceSets {
        commonMain.dependencies {
            implementation(compose.runtime)

            implementation(libs.kotlinxSerializationJson)
            api(libs.kotlinxDatetime)
            api(libs.kotlinxCollectionsImmutable)
            api(libs.soilQueryCore)
        }

        androidMain.dependencies {
            implementation(libs.androidxAppCompat)
        }
    }
}
