plugins {
    id("droidkaigi.primitive.detekt")
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidKotlinMultiplatformLibrary)
    alias(libs.plugins.kotlinxSerialization)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
}

kotlin {
    jvmToolchain(17)

    androidLibrary {
        compileSdk = 36
        namespace = "io.github.droidkaigi.confsched.model"
    }
    jvm()

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

    compilerOptions.freeCompilerArgs.add("-opt-in=kotlin.time.ExperimentalTime")
}
