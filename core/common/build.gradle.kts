plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.composeCompiler)
    id("droidkaigi.primitive.kmp")
    id("droidkaigi.primitive.kmp.ios")
    alias(libs.plugins.metro)
}

dependencies {
    commonMainImplementation(projects.core.model)
    commonMainImplementation(compose.runtime)
    commonMainImplementation(libs.rin)
}
