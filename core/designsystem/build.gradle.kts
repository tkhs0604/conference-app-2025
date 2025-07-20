plugins {
    id("droidkaigi.primitive.kmp")
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.jetbrainsCompose)
    id("droidkaigi.primitive.detekt")
}

dependencies {
    commonMainImplementation(compose.material3)
}
