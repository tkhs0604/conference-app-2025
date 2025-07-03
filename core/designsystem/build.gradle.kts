plugins {
    id("droidkaigi.primitive.kmp")
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.jetbrainsCompose)
}

dependencies {
    commonMainImplementation(compose.material3)
}
